package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)

            // Disable both true and false buttons if you've clicked true
            disableTrueFalseButtons()
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)

            // Disable both true and false buttons if you've clicked false
            disableTrueFalseButtons()
        }

        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

            // Enable the true and false buttons once you've clicked the next button
            enableTrueFalseButtons()

        }

        updateQuestion()
    }

    // 3.3 (p58): Overriding  more lifecycle functions
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }


    // Create a function that disables both the true and false buttons
    private fun disableTrueFalseButtons() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        // Call in binding.trueButton.setOnClickListener && binding.falseButton.setOnClickListener
    }

    // Create a function that enables both the true and false buttons
    private fun enableTrueFalseButtons() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true

        // Call in binding.nextButton.setOnClickListener {}
    }
/*
    I wanted to use a toggle function but if you don't answer the question, it will disable the true/false
        buttons if you've put this function in all the button onClickListeners

    private fun toggleEnableTrueFalseButtons() {
        binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
        binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

    }*/

}
