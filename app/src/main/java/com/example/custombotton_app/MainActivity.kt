package com.example.custombotton_app

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView

private var selectedTab = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val homeLayout: LinearLayout = findViewById(R.id.homeLayout)
        val likeLayout: LinearLayout = findViewById(R.id.likeLayout)
        val notificationLayout: LinearLayout = findViewById(R.id.notificationLayout)
        val profileLayout: LinearLayout = findViewById(R.id.profileLayout)

        val homeImage: ImageView = findViewById(R.id.homeImage)
        val likeImage: ImageView = findViewById(R.id.likeImage)
        val notificationImage: ImageView = findViewById(R.id.notificationImage)
        val profileImage: ImageView = findViewById(R.id.profileImage)

        val homeTxt: TextView = findViewById(R.id.homeTxt)
        val likeTxt: TextView = findViewById(R.id.likeTxt)
        val notificationTxt: TextView = findViewById(R.id.notificationTxt)
        val profileTxt: TextView = findViewById(R.id.profileTxt)

        supportFragmentManager.beginTransaction().setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, HomeFragment::class.java, null)
            .commit()

        homeLayout.setOnClickListener {
            if (selectedTab != 1) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, HomeFragment::class.java, null)
                    .commit()

                hideTexts(likeTxt, notificationTxt, profileTxt)

                homeImage.setImageResource(R.drawable.baseline_home_selected)
                updateImageResource(likeImage, R.drawable.favorite)
                updateImageResource(notificationImage, R.drawable.baseline_notifications_24)
                updateImageResource(profileImage, R.drawable.person)

                resetBackgrounds(likeLayout, notificationLayout, profileLayout)

                homeTxt.visibility = View.VISIBLE
                homeLayout.setBackgroundResource(R.drawable.round_back_home_100)
                applyScaleAnimation(homeLayout)

                selectedTab = 1
            }
        }

        likeLayout.setOnClickListener {
            if (selectedTab != 2) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, LikesFragment::class.java, null)
                    .commit()

                hideTexts(homeTxt, notificationTxt, profileTxt)

                updateImageResource(homeImage, R.drawable.baseline_home_24)
                likeImage.setImageResource(R.drawable.favorite_selected)
                updateImageResource(profileImage, R.drawable.person)

                resetBackgrounds(homeLayout, notificationLayout, profileLayout)

                likeTxt.visibility = View.VISIBLE
                likeLayout.setBackgroundResource(R.drawable.round_back_like_100)
                applyScaleAnimation(likeLayout)

                selectedTab = 2
            }
        }

        notificationLayout.setOnClickListener {
            if (selectedTab != 3) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, NotificationFragment::class.java, null)
                    .commit()

                hideTexts(homeTxt, likeTxt, profileTxt)

                updateImageResource(homeImage, R.drawable.baseline_home_24)
                updateImageResource(likeImage, R.drawable.favorite)
                updateImageResource(profileImage, R.drawable.person)

                resetBackgrounds(homeLayout, likeLayout, profileLayout)

                notificationTxt.visibility = View.VISIBLE
                notificationImage.setImageResource(R.drawable.baseline_notifications_setected)
                notificationLayout.setBackgroundResource(R.drawable.round_back_notification_100)
                applyScaleAnimation(notificationLayout)

                selectedTab = 3
            }
        }

        profileLayout.setOnClickListener {
            if (selectedTab != 4) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, ProfileFragment::class.java, null)
                    .commit()

                hideTexts(homeTxt, likeTxt, notificationTxt)

                updateImageResource(homeImage, R.drawable.baseline_home_24)
                updateImageResource(likeImage, R.drawable.favorite)
                notificationImage.setImageResource(R.drawable.baseline_notifications_24)

                resetBackgrounds(homeLayout, likeLayout, notificationLayout)

                profileTxt.visibility = View.VISIBLE
                profileImage.setImageResource(R.drawable.person_selected)
                profileLayout.setBackgroundResource(R.drawable.round_back_profile_100)
                applyScaleAnimation(profileLayout)

                selectedTab = 4
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun hideTexts(vararg texts: TextView) {
        for (text in texts) {
            text.visibility = View.GONE
        }
    }

    private fun updateImageResource(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    private fun resetBackgrounds(vararg layouts: LinearLayout) {
        for (layout in layouts) {
            layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        }
    }

    private fun applyScaleAnimation(view: View) {
        val scaleAnimation = ScaleAnimation(
            0.8f, 1.0f, 1f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 200
        scaleAnimation.fillAfter = true
        view.startAnimation(scaleAnimation)
    }
}
