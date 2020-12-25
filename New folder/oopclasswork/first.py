class bank_account (object):
    def __init__ (self, balance): # You can also use a default: def __init__(self, balance = 500)
        self.balance = balance 
        b = bank_account(200) # this creates an instance b of type bank_account. It also sets b’s balance.
        print (b.balance) # 200
        def deposit(self, d_amount):
            self.balance = self.balance + d_amount

        def deposit(self, d_amount = 100): # default value to create an account if no value is given
             self.balance = self.balance + d_amount

        def withdraw(self, w_amount):
            self.balance = self.balance – w_amount 
    