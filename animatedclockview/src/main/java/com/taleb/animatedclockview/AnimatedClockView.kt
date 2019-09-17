package com.taleb.animatedclockview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

public class AnimatedClockView : View {
    private val clockPaint = Paint()
    private val startAngle: Double = 3.0 * PI / 2.0
    private var bigHandAngle: Double = startAngle
    private var smallHandAngle: Double = startAngle
    private val bigHandChange = 2 * PI / 60.0
    private val smallHandChange = bigHandChange / 12
    //
    public var clockColor: Int = Color.BLACK
        set(value) {
            field = value
            postInvalidate()
        }
    public var animate: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                postInvalidate()
            }
        }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : super(context, attrs, defStyleAttrs) {
        init(attrs)
    }


    private fun init(attrs: AttributeSet?) {

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimatedClockView)
            try {
                clockColor = typedArray.getColor(R.styleable.AnimatedClockView_acv_clockColor, Color.BLACK)
                animate = typedArray.getBoolean(R.styleable.AnimatedClockView_acv_animate, false)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                typedArray.recycle()
            }
        }

        clockPaint.style = Paint.Style.STROKE
        clockPaint.isAntiAlias = true
        clockPaint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) drawClock(canvas)
    }


    private fun drawClock(canvas: Canvas) {
        val maxPadding =
            max(max(max(paddingRight, paddingLeft), max(paddingStart, paddingEnd)), max(paddingTop, paddingBottom))
        var circleRadius = ((min(width, height) / 2.toFloat())) - maxPadding
        val clockStrokeWidth: Float = (circleRadius * 0.05f) * resources.displayMetrics.density
        circleRadius -= clockStrokeWidth
        val centerX: Float = (width / 2).toFloat()
        val centerY: Float = (height / 2).toFloat()
        //
        clockPaint.color = clockColor
        clockPaint.strokeWidth = clockStrokeWidth
        //
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), circleRadius, clockPaint)

        //draw clock hands
        val bigHandRadius = circleRadius - 2 * clockStrokeWidth
        val bigHandRadiusStopX = (bigHandRadius * Math.cos(bigHandAngle) + centerX).toFloat()
        val bigHandRadiusStopY = (bigHandRadius * Math.sin(bigHandAngle) + centerY).toFloat()
        canvas.drawLine(centerX, centerY, bigHandRadiusStopX, bigHandRadiusStopY, clockPaint)
        //small hand
        val smallHandRadius = 2 * bigHandRadius / 3
        val smallHandRadiusStopX = (smallHandRadius * Math.cos(smallHandAngle) + centerX).toFloat()
        val smallHandRadiusStopY = (smallHandRadius * Math.sin(smallHandAngle) + centerY).toFloat()
        canvas.drawLine(centerX, centerY, smallHandRadiusStopX, smallHandRadiusStopY, clockPaint)

        if (animate) {
            bigHandAngle += bigHandChange
            smallHandAngle += smallHandChange

            if ((bigHandAngle % startAngle) == 0.0)
                bigHandAngle = startAngle
            if ((smallHandAngle % startAngle) == 0.0)
                smallHandAngle = startAngle

            postInvalidate()
        }
    }

    public fun resetClock(){
        bigHandAngle = startAngle
        smallHandAngle = startAngle
        animate = false
        postInvalidate()
    }


}