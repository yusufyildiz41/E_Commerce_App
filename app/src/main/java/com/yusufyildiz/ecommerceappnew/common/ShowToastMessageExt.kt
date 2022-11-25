package com.yusufyildiz.ecommerceappnew.common

import android.content.Context
import android.widget.Toast

infix fun Context?.showToast(text : String) = Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
