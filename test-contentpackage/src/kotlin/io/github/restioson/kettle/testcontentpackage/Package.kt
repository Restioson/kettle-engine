package io.github.restioson.kettle.testcontentpackage

import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.ServerSidePackage

class Package : ContentPackage {

    override lateinit var engine: Kettle
    override lateinit var kClientSide: ClientSidePackage
    override lateinit var kServerSide: ServerSidePackage

    override fun initClient() {
        this.kClientSide = TestClientSide(this.engine, this.kServerSide.kLevel as TestLevel, 640f, 360f)
        this.kClientSide.init()
    }

    override fun create() {
        this.kServerSide = TestServerSide()
    }

}