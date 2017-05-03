package com.ggaier.gigagalk.rectanglecirclecollision

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class RectangleCircleCollisionScreen : ScreenAdapter() {

    private val WORLD_SIZE = 100f
    private val CIRCLE_SEGMENT = 64

    private val mStartTime = TimeUtils.nanoTime()
    private val mShapeRenderer: ShapeRenderer = ShapeRenderer()
    private val mViewport: FitViewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
    private val mRectangles: Array<Rectangle> = Array()
    private val mCircles: Array<OscillatingCircle> = Array()

    init {
        mRectangles.add(Rectangle(40f, 40f, 20f, 20f))
        mRectangles.add(Rectangle(10f, 40f, 10f, 20f))
        mRectangles.add(Rectangle(70f, 45f, 20f, 10f))

        mCircles.add(OscillatingCircle(50f, 65f, 7f, 0f, 40f, 3f))
        mCircles.add(OscillatingCircle(50f, 35f, 7f, 0f, 40f, 3.1f))
        mCircles.add(OscillatingCircle(50f, 50f, 3f, MathUtils.PI / 4, 40f, 5f))
        mCircles.add(OscillatingCircle(25f, 50f, 5f, 0f, 11f, 7f))
    }


    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        val elapsedTime = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(mStartTime)
        mViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        mShapeRenderer.projectionMatrix = mViewport.camera.combined
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        mShapeRenderer.setColor(1f, 1f, 1f, 1f)

        for (rectangle in mRectangles) {
            mShapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width,
                    rectangle.height)
        }
        mCircles
                .map { it.getCurrentCircle(elapsedTime) }
                .map { circle -> mRectangles.any { areColliding(it, circle) } }
                .forEach {
                    if (it) {
                        mShapeRenderer.setColor(1f, 0f, 0f, 1f)
                    } else {
                        mShapeRenderer.setColor(0f, 1f, 0f, 1f)
                    }
                }

        for(oscillatingCircle in mCircles){
            val circle =oscillatingCircle.getCurrentCircle(elapsedTime)
            val colliding= mRectangles.any { areColliding(it,circle) }
            if (colliding) {
                mShapeRenderer.setColor(1f, 0f, 0f, 1f)
            } else {
                mShapeRenderer.setColor(0f, 1f, 0f, 1f)
            }
            mShapeRenderer.circle(circle.x,circle.y,circle.radius,CIRCLE_SEGMENT)
        }

        mShapeRenderer.end()

    }

    private fun areColliding(rect: Rectangle, circle: Circle): Boolean {
        val containsACorner = circle.contains(rect.x, rect.y) ||
                circle.contains(rect.x + rect.width, rect.y) || circle.contains(rect.x,
                rect.y + rect.height) || circle.contains(rect.x + rect.width, rect.y + rect.height)
        val inHorizontalInterval = rect.x < circle.x && circle.x < rect.x + rect.width
        val inVerticalInterval = rect.y < circle.y && circle.y < rect.y + rect.height
        val inHorizontalNeighborhood = rect.x - circle.radius < circle.x &&
                circle.x < rect.x + rect.width + circle.radius
        val inVerticalNeighborhood = rect.y - circle.radius < circle.y
                && circle.y < rect.y + rect.height + circle.radius

        val touchingAnEdge = inHorizontalInterval && inVerticalNeighborhood ||
                inVerticalInterval && inHorizontalNeighborhood
        return touchingAnEdge || containsACorner
    }


}