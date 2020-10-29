package com.example.soccercodding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt application component for the whole application - Singleton
@HiltAndroidApp
class MyApplication : Application()