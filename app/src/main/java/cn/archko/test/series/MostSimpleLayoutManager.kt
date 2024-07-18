package cn.archko.test.series

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

/**
 * Created by @juliswang on 2023/10/19 19:58
 *
 * @Description
 *
 * 实现一个最简单的 LayoutManager，支持数据的填充，上下左右自由滑动，如果要成为一个 RecyclerView 可用的 LayoutManager，
 *      接下来还需要实现:
 *          1、数据填充只需要填充屏幕范围内的 ItemView
 *          2、回收掉屏幕以外的 ItemView
 *          3、屏幕外 ItemView 再回到屏幕后数据需要重新填充
 *          4、对滑动边界进行处理
 *          5、对 scrollToPosition 进行支持
 *          6、对 smoothScrollToPosition 进行支持
 *
 *          ...其他更复杂的功能进行支持
 *
 */
class MostSimpleLayoutManager : RecyclerView.LayoutManager() {
    override fun isAutoMeasureEnabled(): Boolean {
        return true
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        // 垂直方向的偏移量
        var offsetTop = 0
        // 实际业务中最好不要这样一次性加载所有的数据，这里只是最简单地演示一下整体是如何工作的
        var itemWidth = 0.0
        for (itemIndex in 0 until itemCount) {
            // 从适配器获取与给定位置关联的视图
            val itemView = recycler.getViewForPosition(itemIndex)
            // 将视图添加到 RecyclerView 中
            addView(itemView)
            // 测量并布局视图
            measureChildWithMargins(itemView, 0, 0)
            // 拿到宽高（包括ItemDecoration）
            val width = 1.5 * getDecoratedMeasuredWidth(itemView)
            val height = (1.5 * getDecoratedMeasuredHeight(itemView)).toInt()
            if (itemWidth == 0.0) {
                itemWidth = width
            }
            // 对要添加的子 View 进行布局
            layoutDecorated(itemView, 0, offsetTop, width.toInt(), offsetTop + height)
            offsetTop += height
        }

        mTotalWidth = max(itemWidth, horizontalSpace.toDouble()).toInt()
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    /**
     * 根据屏幕横向滑动的距离dx计算实际滑动的距离。
     * 默认不进行滑动，返回值为0
     *
     * @param dx
     * @param recycler
     * @param state
     * @return 实际滚动的距离，如果 dx 为负并且朝该方向继续滚动，则返回值将为负
     */
    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        // 在这里处理左右滚动逻辑，dx 表示滚动的距离
        // 平移所有子视图
        //offsetChildrenHorizontal(-dx)
        // 如果实际滚动距离与 dx 相同，返回 dx；如果未滚动，返回 0
        var dx1 = dx
        //detachAndScrapAttachedViews(recycler!!)
        if (mHorizontalOffset + dx1 < 0) {
            dx1 = -mHorizontalOffset
        } else if (mHorizontalOffset + dx1 > mTotalWidth - horizontalSpace) {
            dx1 = mTotalWidth - horizontalSpace - mHorizontalOffset
        }
        println("mTotalWidth:$mTotalWidth,dx:$dx1")
        offsetChildrenHorizontal(-dx1)
        mHorizontalOffset += dx1
        return dx
    }

    private var mHorizontalOffset = 0
    private var mVerticalOffset = 0
    private var mTotalWidth = 0
    private val horizontalSpace: Int
        private get() = width - paddingLeft - paddingRight
    private val verticalSpace: Int
        private get() = height - paddingTop - paddingBottom

    /**
     * 根据屏幕纵向滑动的距离dy计算实际滑动的距离。
     * 默认不进行滑动，返回值为0
     *
     * @param dy
     * @param recycler
     * @param state
     * @return 如果 dy 为负并且朝该方向继续滚动，则返回值将为负
     */
    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        // 在这里处理上下的滚动逻辑，dy 表示滚动的距离
        // 平移所有子视图
        offsetChildrenVertical(-dy)
        // 如果实际滚动距离与 dy 相同，返回 dy；如果未滚动，返回 0
        //var dy1 = dy
        //detachAndScrapAttachedViews(recycler)
        //if (mVerticalOffset + dy1 < 0) {
        //    dy1 = -mVerticalOffset
        //} else if (mVerticalOffset + dy1 > mTotalHeight - verticalSpace) {
        //    dy1 = mTotalHeight - verticalSpace - mVerticalOffset
        //}
        //offsetChildrenVertical(-dy1)
        //mVerticalOffset += dy1
        return dy
    }
}
