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
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DesertPlaceholder : FrameLayout {

    private lateinit var button: TextView
    private lateinit var message: TextView

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context)
            .inflate(
                R.layout.placeholder,
                this,
                true
            )

        message = findViewById(R.id.placeholder_message)
        button  = findViewById(R.id.placeholder_button)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DesertPlaceholder)
        try {
            with(attributes) {
                setMessage(getString(R.styleable.DesertPlaceholder_dp_message))
                setButtonText(getString(R.styleable.DesertPlaceholder_dp_buttonText))
            }
        } finally {
            attributes.recycle()
        }
        setBackgroundColor(ContextCompat.getColor(context, R.color.background_desert))
    }

    fun setOnButtonClickListener(callback: (v: View) -> Unit) =
        button.setOnClickListener(callback)

    fun setMessage(msg: String?) {
        message.text = msg
    }

    fun setButtonText(action: String?) {
        with(button) {
            if (TextUtils.isEmpty(action)) {
                visibility = View.GONE
            } else {
                text = action
                visibility = View.VISIBLE
            }
        }
    }

    companion object {
        var animationEnabled = true
    }

}

fun AppCompatActivity.desertPlaceHolderAction(@IdRes id: Int, callback: (view: View) -> Unit) {
    findViewById<DesertPlaceholder>(id)
        .setOnButtonClickListener(callback)
}
