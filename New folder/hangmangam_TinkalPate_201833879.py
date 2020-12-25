#################################### Mid sem 
######@author  Tinkal Patel
import random
import sys

listOfWords = ['Python','Scripting','Mississippi','excellent','language']

guess_word = []
secretWord = random.choice(listOfWords) # lets randomize single word from the list
length_word = len(secretWord)
alphabet = "abcdefghijklmnopqrstuvwxyz"
letter_storage = []

############# ask the user if he/she wants to play 
def newFunc():
    print("Well, that's perfect moment to play some Hangman!\n")

    while True:
        gameChoice = input("Would You like to play?\n").upper()

        if gameChoice == "YES" or gameChoice == "Y":
            break
        elif gameChoice == "NO" or gameChoice == "N":
            sys.exit(" Have a nice day")
        else:
            print("Please Answer only Yes or No")
            continue

newFunc()

###
# 
# 
# 
# ####  printing a underscore per letter
def change():
    
    for character in secretWord: # printing blanks for each letter in secret word
        guess_word.append("-")

    print("Ok, so the word You need to guess has", length_word, "characters")

    print("Be aware that You can enter only 1 letter from a-z\n\n")

    print(guess_word)
   # print(secretWord)

############  Its time to guess 
def guessing():
    guess_taken = 1

    while guess_taken <= (length_word)/2:


        guess = input("Pick a letter\n").lower()

        if not guess in alphabet: #checking input
            print("Enter a letter from a-z alphabet")
        elif guess in letter_storage: #checking if letter has been already used
            print("You have already guessed that letter!")
        else: 

            letter_storage.append(guess)
            if guess in secretWord:
                print("You guessed correctly!")
                for x in range(0, length_word): 
                    if secretWord[x] == guess:
                        guess_word[x] = guess
                        print(guess_word)

                if not '-' in guess_word:
                    print("You won!")
                    break
            else:
                print("The letter is not in the word. Try Again!")
                guess_taken += 1
            if guess_taken == 10:
                print(" Sorry Mate, You lost :<! The secret word was",         secretWord)


change()
guessing()

print("Game Over!")