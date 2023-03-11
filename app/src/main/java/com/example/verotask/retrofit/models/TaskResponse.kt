package com.example.verotask.retrofit.models

data class TaskResponse(
    val task: String,
    val title: String,
    val description: String,
    val sort: String,
    val wageType: String,
    val BusinessUnitKey: String,
    val businessUnit: String,
    val parentTaskID: String,
    val preplanningBoardQuickSelect: Int,
    val colorCode: String,
    val workingTime: Int,
    val isAvailableInTimeTrackingKioskMode: Boolean

)

/*
        "task": "10 Aufbau",
        "title": "Gerüst montieren",
        "description": "Gerüste montieren.",
        "sort": "0",
        "wageType": "10 Aufbau",
        "BusinessUnitKey": "Gerüstbau",
        "businessUnit": "Gerüstbau",
        "parentTaskID": "",
        "preplanningBoardQuickSelect": null,
        "colorCode": "#1df70e",
        "workingTime": null,
        "isAvailableInTimeTrackingKioskMode": false
 */
