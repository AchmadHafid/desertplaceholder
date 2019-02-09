/*
 * Copyright (C) 2016 JetRadar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetradar.desertplaceholder

import android.content.Context
import android.graphics.*
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.View
import java.util.*

class TumbleweedView : View {

    private var density: Float = 0F
    private var timeStamp = INVALID_TIME.toDouble()
    private var meterInDp: Float = 0F
    private var currentVerticalSpeed: Float = 0F
    private var currentSpeed = DEFAULT_SPEED

    private var tumbleweed: Bitmap? = null
    private var shadow: Bitmap? = null
    private var random: Random? = null
    internal var x: Float = 0F
    internal var y: Float = 0F
    private var angle: Float = 0F
    private var paint: Paint? = null
    internal val matrix = Matrix()
    private var bottomPosition: Float = 0F
    private var topPosition: Float = 0F

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        paint      = Paint()
        tumbleweed = BitmapFactory.decodeResource(context.resources, R.drawable.tumbleweed)
        shadow     = BitmapFactory.decodeResource(context.resources, R.drawable.shadow_tumbleweed)
        density    = context.resources.displayMetrics.density
        random     = Random()
        meterInDp  = tumbleweed!!.height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        val time = System.currentTimeMillis()

        if (timeStamp != INVALID_TIME.toDouble()) {
            val delta = (time - timeStamp) / 1000.0
            updatePosition(delta)
            drawShadow(canvas)
            drawTumbleweed(canvas, delta)
        } else {
            bottomPosition = (height - tumbleweed!!.height).toFloat()
            topPosition = MAX_JUMP_HEIGHT_IN_METERS * density
            x = width * START_POSITION_PERCENT_OF_SCREEN
            y = bottomPosition
        }

        timeStamp = time.toDouble()
        if (DesertPlaceholder.animationEnabled) {
            invalidate()
        }
    }

    private fun drawShadow(canvas: Canvas) {
        val scale = 1 - SHADOW_SCALE_FACTOR * ((bottomPosition - y) / (bottomPosition - topPosition))
        val toDraw: Bitmap?
        toDraw = if (scale == 1f) {
            shadow
        } else {
            val width = Math.round(shadow!!.width * scale)
            val height = Math.round(shadow!!.height * scale)
            if (width == 0 || height == 0) return
            Bitmap.createScaledBitmap(shadow!!, width, height, true)
        }
        canvas.drawBitmap(toDraw!!, x, (height - shadow!!.height).toFloat(), paint)
    }

    private fun drawTumbleweed(canvas: Canvas, delta: Double) {
        updateAngle(delta)
        matrix.setTranslate(x, y)
        matrix.postRotate(angle, x + tumbleweed!!.width.toFloat() / 2, y + tumbleweed!!.height.toFloat() / 2)
        canvas.drawBitmap(tumbleweed!!, matrix, paint)
    }

    private fun updateAngle(delta: Double) {
        angle += (delta * ROTATION_SPEED).toFloat()
        angle %= 360f
    }

    private fun updatePosition(delta: Double) {
        recalculateCurrentSpeed()
        x += (density.toDouble() * currentSpeed.toDouble() * delta).toFloat()
        if (x > width + 5 * tumbleweed!!.width) {
            x = (-tumbleweed!!.width).toFloat()
        }

        if (y != bottomPosition || currentVerticalSpeed > 0) {
            currentVerticalSpeed -= (delta * g.toDouble() * meterInDp.toDouble()).toFloat()
            y -= (delta * currentVerticalSpeed).toFloat()
            if (y > bottomPosition) {
                y = bottomPosition
            }
        } else {
            calculateJumpSpeed()
        }
    }

    private fun calculateJumpSpeed() {
        currentVerticalSpeed =
            getRandom(0.2f * MAX_JUMP_HEIGHT_IN_METERS, MAX_JUMP_HEIGHT_IN_METERS) * meterInDp * density
    }

    private fun recalculateCurrentSpeed() {
        currentSpeed += getRandom(-SPEED_RANDOM_DELTA, SPEED_RANDOM_DELTA)

        if (currentSpeed < MIN_SPEED) {
            currentSpeed = MIN_SPEED
        }
        if (currentSpeed > MAX_SPEED) {
            currentSpeed = MAX_SPEED
        }
    }

    private fun getRandom(min: Float, max: Float): Float {
        val random = this.random!!.nextFloat()
        return min + random * (max - min)
    }

    companion object {
        private const val INVALID_TIME = -1
        private const val START_POSITION_PERCENT_OF_SCREEN = 0.3f
        private const val SHADOW_SCALE_FACTOR = 0.7f
        private const val MAX_JUMP_HEIGHT_IN_METERS = 1f
        private const val DEFAULT_SPEED = 50f // dp/sec
        private const val MAX_SPEED = DEFAULT_SPEED * 1.2f
        private const val MIN_SPEED = DEFAULT_SPEED * 0.8f
        private const val SPEED_RANDOM_DELTA = 0.05f * DEFAULT_SPEED
        private const val ROTATION_SPEED = 360f
        private val g = SensorManager.GRAVITY_MARS
    }

}
