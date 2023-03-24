package com.example.squeezegame.game

import android.util.Log

class SquezeGame(var minNumber: Int, var maxNumber: Int) {
    var status: GamesStatus = GamesStatus.PLAYING
    var secretNumber: Int = (minNumber..maxNumber).random()

    fun init(minNumber: Int, maxNumber: Int) {
        this.secretNumber = (minNumber..maxNumber).random()
        this.status = GamesStatus.PLAYING
        Log.i("SquezeGame", "Secret number is $secretNumber")
    }

    fun guess(number: Int): Int {
        if (this.invalidGuess(number)) {
            Log.i("SquezeGame", "Invalid guess $number")
            this.status = GamesStatus.LOST
            return 0
        } else {
            Log.i("SquezeGame", "Valid guess $number")
            val value = this.updateInterval(number)
            if (this.squeezed()) {
                this.status = GamesStatus.WON
            }
            return value
        }
    }

    private fun invalidGuess(guess: Int): Boolean {
        return !validGuess(guess)
    }

    private fun validGuess(guess: Int): Boolean {
        return guess > this.minNumber && guess < this.maxNumber && guess != this.secretNumber
    }

    private fun squeezed(): Boolean {
        return this.minNumber + 1 == this.maxNumber - 1
    }

    private fun updateInterval(guess: Int): Int {
        return if (guess < this.secretNumber) {
            this.minNumber = guess
            -1
        } else {
            this.maxNumber = guess
            1
        }
    }

}