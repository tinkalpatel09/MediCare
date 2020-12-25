key = 'abcdefghijklmnopqrstuvwxyz'

def Caesar_cipher(n, plaintext):
    """Encrypt the string and return the ciphertext"""
    result = ''

    for l in plaintext.lower():
        try:
            i = (key.index(l) + n) % 26
            result += key[i]
        except ValueError:
            result += l

    return result.lower()

text = input("Enter the text: ")
offset = 3

encrypted = Caesar_cipher(offset, text)
print('Encrypted: ', encrypted)