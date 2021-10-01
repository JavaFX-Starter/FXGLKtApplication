package com.icuxika

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.dsl.components.ProjectileComponent
import javafx.geometry.Point2D
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.util.Duration

fun main() {
    GameApplication.launch(MainApp::class.java, emptyArray())
}

class MainApp : GameApplication() {
    enum class Type {
        BULLET, TARGET
    }

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 720
            height = 640
            title = Info.title
            version = Info.version
            isProfilingEnabled = true
        }
    }

    override fun initInput() {
        onKeyDown(KeyCode.F, "Shoot") {
            shoot()
        }
    }

    override fun initGame() {
        run({
            spawnTarget()
        }, Duration.seconds(1.0))
    }

    override fun initPhysics() {
        onCollisionBegin(Type.BULLET, Type.TARGET) { bullet, target ->
            bullet.removeFromWorld()
            target.removeFromWorld()
        }
    }

    private fun shoot() {
        entityBuilder()
            .type(Type.BULLET)
            .at(getAppWidth() / 2.0, getAppHeight().toDouble())
            .viewWithBBox(Rectangle(10.0, 5.0, Color.BLUE))
            .collidable()
            .with(ProjectileComponent(Point2D(0.0, -1.0), 350.0))
            .buildAndAttach()
    }

    private fun spawnTarget() {
        entityBuilder()
            .type(Type.TARGET)
            .at(0.0, getAppHeight() / 2.0)
            .viewWithBBox(Rectangle(40.0, 40.0, Color.RED))
            .collidable()
            .with(ProjectileComponent(Point2D(1.0, 0.0), 100.0))
            .buildAndAttach()
    }
}