package cn.archko.test.series

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by @juliswang on 2023/10/19 19:58
 *
 * @Description 对重新进入屏幕内的 ItemView 主要是向上滑动的时View的填充
 *
 */
class LinearLayoutManager3 : RecyclerView.LayoutManager() {
    private val TAG = "JLayoutManager"

    private val orientationVerticalHelper = OrientationHelper.createVerticalHelper(this)

    override fun isAutoMeasureEnabled(): Boolean {
        return true
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        // [detachAndScrapAttachedViews]
        // 它的主要作用是将所有已附加到 RecyclerView 的子视图（即 item）从布局中分离并放入回收池（RecyclerPool）
        // 调用 detachAndScrapAttachedViews() 时，LayoutManager 会将所有当前附加的子视图从布局中分离，但不会将它们从回收池中移除。
        // 这意味着这些视图仍然可以被回收和复用，从而提高性能和内存效率。
        // 在自定义 LayoutManager 的 onLayoutChildren() 方法中，通常需要在布局新的子视图之前调用 detachAndScrapAttachedViews()。
        // 这样可以确保 RecyclerView 在重新布局时正确处理所有子视图的回收和复用。
        detachAndScrapAttachedViews(recycler)

        // 垂直方向上的的空间大小
        var remainSpace = orientationVerticalHelper.totalSpace
        //垂直方向的偏移量
        var offsetTop = 0
        var currentPosition = 0

        while (remainSpace > 0 && currentPosition < state.itemCount) {
            // 从适配器获取与给定位置关联的视图
            val itemView = recycler.getViewForPosition(currentPosition)
            // 将视图添加到 RecyclerView 中
            addView(itemView)
            // 测量并布局视图
            measureChildWithMargins(itemView, 0, 0)
            // 拿到宽高（包括ItemDecoration）
            val itemWidth = getDecoratedMeasuredWidth(itemView)
            val itemHeight = getDecoratedMeasuredHeight(itemView)
            // 对要添加的子 View 进行布局
            layoutDecorated(itemView, 0, offsetTop, itemWidth, offsetTop + itemHeight)
            offsetTop += itemHeight
            currentPosition++
            // 可用空间减少
            remainSpace -= itemHeight
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        // 在这里处理左右滚动逻辑，dx 表示滚动的距离
        // 平移所有子视图
        offsetChildrenHorizontal(-dx)
        // 如果实际滚动距离与 dx 相同，返回 dx；如果未滚动，返回 0
        return dx
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State?
    ): Int {
        // 填充 view
        fillView(dy, recycler)
        // 移动 view
        offsetChildrenVertical(-dy)
        // 回收 View
        recycleInvisibleView(dy, recycler)
        return dy
    }

    /**
     * 填充重新进入屏幕内的 ItemView
     *     getChildCount():childCount-> 当前屏幕内RecyclerView展示的 ItemView 数量
     *     getItemCount():itemCount-> 最大的 ItemView 数量，也就是 Adapter 传递的数据的数量
     */
    private fun fillView(dy: Int, recycler: RecyclerView.Recycler) {
        val verticalSpace = orientationVerticalHelper.totalSpace
        var remainSpace = 0
        var nextFillPosition = 0
        //垂直方向的偏移量
        var offsetTop = 0
        var offsetLeft = 0
        // 从下往上滑，那么需要向底部添加数据
        if (dy > 0) {
            val anchorView = getChildAt(childCount - 1) ?: return
            val anchorPosition = getPosition(anchorView)
            val anchorBottom = getDecoratedBottom(anchorView)
            val anchorLeft = getDecoratedLeft(anchorView)
            remainSpace = verticalSpace - anchorBottom
            // 垂直可用的数据为<0，意外着这时候屏幕底部的位置刚好在最底部的 ItemView 上，还需要向上滑动一点点...我们才能添加 View
            if (remainSpace < 0) {
                return
            }
            nextFillPosition = anchorPosition + 1
            offsetTop = anchorBottom
            offsetLeft = anchorLeft
            if (nextFillPosition >= itemCount) {
                return
            }
        } else if (dy < 0) {  // 从上往下滑，那么需要向顶部添加数据
            //no-op 暂时不实现从上往下滑的底部数据填充
        }

        while (remainSpace > 0 && nextFillPosition < itemCount) {
            // 从适配器获取与给定位置关联的视图
            val itemView = recycler.getViewForPosition(nextFillPosition)
            // 将视图添加到 RecyclerView 中
            addView(itemView)
            // 测量并布局视图
            measureChildWithMargins(itemView, 0, 0)
            // 拿到宽高（包括ItemDecoration）
            val itemWidth = getDecoratedMeasuredWidth(itemView)
            val itemHeight = getDecoratedMeasuredHeight(itemView)
            // 对要添加的子 View 进行布局，相比onLayoutChildren 里面的实现添加了：offsetLeft（因为我们没有禁止掉 左右的滑动）
            // 试着把 offsetLeft 改成0，也就是最原始的样子，然后左右上下滑滑，你会有意外收获
            layoutDecorated(
                itemView,
                offsetLeft,
                offsetTop,
                itemWidth + offsetLeft,
                offsetTop + itemHeight
            )
            offsetTop += itemHeight
            nextFillPosition++
            // 可用空间减少
            remainSpace -= itemHeight
        }
    }

    /**
     * 回收掉在界面上看不到的 ItemView
     *
     * @param dy
     * @param recycler
     */
    private fun recycleInvisibleView(dy: Int, recycler: RecyclerView.Recycler) {
        val totalSpace = orientationVerticalHelper.totalSpace

        // 将要回收View的集合
        val recycleViews = hashSetOf<View>()
        // 从下往上滑
        if (dy > 0) {
            for (i in 0 until childCount) {
                val child = getChildAt(i)!!
                // 从下往上滑从最上面的 item 开始计算
                val top = getDecoratedTop(child)
                // 判断最顶部的 item 是否已经完全不可见，如何可见，那说明底下的 item 也是可见
                val height = top - getDecoratedBottom(child)
                if (height - top < 0) {
                    break
                }
                recycleViews.add(child)
            }
        } else if (dy < 0) {  // 从上往下滑
            for (i in childCount - 1 downTo 0) {
                val child = getChildAt(i)!!
                // 从上往下滑从最底部的 item 开始计算
                val bottom = getDecoratedBottom(child)
                // 判断最底部的 item 是否已经完全不可见，如何可见，那说明上面的 item 也是可见
                val height = bottom - getDecoratedTop(child)
                if (bottom - totalSpace < height) {
                    break
                }
                recycleViews.add(child)
            }
        }

        // 真正把 View 移除掉的逻辑
        for (view in recycleViews) {
            // [removeAndRecycleView]
            // 用于从视图层次结构中删除某个视图，并将其资源回收，以便在需要时重新利用
            removeAndRecycleView(view, recycler)
        }
        recycleViews.clear()
        Log.d(TAG, "[recycleInvisibleView] child count:$childCount")
    }
}