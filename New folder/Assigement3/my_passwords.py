import string
import random

import module


def random_pwd(size = 8, chars=string.ascii_letters + string.digits + string.punctuation):
    print("A good password for you could be: ",''.join(random.choice(chars) for _ in range(size)))

option=int(input("\n1.Checking whether or not your password is “good” \n2.Generating a random password instead.\n"))
if (option == 1):
    passwd = input("\nPlease enter your password: ")
    module.pwd_check(passwd)
elif (option == 2):
    print(random_pwd(int(input('\nHow many characters would you like to have in your password ?\n'))))
else:
    print("\nyou have to choose between 1 and 2 only")
    