package controllers.test

import com.google.gson.Gson
import controllers.base.BaseController
import controllers.test.models.TestRequest
import controllers.test.responses.TestOkResponse
import daggerServerComponent
import spark.Spark.post
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
@author boolenull on 17.03.2019
 */

class TestController: BaseController {

    @Inject
    lateinit var gson: Gson

    init {
        daggerServerComponent.inject(this)
    }

    override fun start() {
        println("TestController is enabled")
        initTestPostResponse()
    }

    private fun initTestPostResponse() {
        post("/api/test", { req, res ->
            val time = SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().time)
            println("[LOG][TEST] Connected from ${req.ip()} in $time")
            val data = gson.fromJson(req.body(), TestRequest::class.java)

            val stringToReturn = "You got from server ${data.message}"
            TestOkResponse(stringToReturn)
        }, gson::toJson)
    }
}