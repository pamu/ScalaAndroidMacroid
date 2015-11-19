package com.fortysevendeg.scala.android.ui.circularreveal

import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentActivity}
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import macroid.{Ui, Contexts}
import com.fortysevendeg.macroid.extras.FragmentExtras._
import macroid.FullDsl._

class CircularRevealActivity
  extends AppCompatActivity
  with Contexts[FragmentActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar foreach setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

  }

  def remove(fragment: Fragment): Unit = removeFragment(fragment)

  override def onOptionsItemSelected(item: MenuItem): Boolean = item.getItemId match {
    case android.R.id.home =>
      finish()
      false
    case _ => super.onOptionsItemSelected(item)
  }

  override def onBackPressed(): Unit =
    findFragmentByTag[SampleFragment](fragmentName) match {
      case Some(f) =>
        runUi(Ui(f.unreveal()) ~
          (circleButton <~ pmdAnimIcon(ADD)))
      case _ => super.onBackPressed()
    }

}
