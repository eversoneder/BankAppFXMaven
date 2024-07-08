# Econo Bank App Structure

## Main Screen
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/MainScene.png" alt="Econo Bank Main Scene" width="400">
A welcome page with options to sign in or sign up.

## Sign-Up Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/SignUpScene.png" alt="Econo Bank Sign Up Scene" width="400">
Requires three fields for creating an account: email, password, and confirm password.

## Sign-In Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/SignInScene.png" alt="Econo Bank Sign In Scene" width="400">
- Includes a "forgot password" button.
- Users must enter their email and bank account number to prove ownership.
- If the user hasn't provided their name and surname, a required field prompts them to do so.

## Account Overview Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/AccountOverviewScene.png" alt="Econo Bank Account Overview Scene" width="400">
- Displays a welcome message on the account overview page with the user's name and surname.
- Buttons for various actions:
  - Withdraw: Specify the withdrawal amount.
  - Deposit: Enter the deposit amount.
  - Transfer: Requires recipient's email and transfer amount.
  - Balance: Displays total balance and latest transactions.
  - Statement: A scrollable dialog showing all transaction history (email, amount, date).
  - Logout: Clears user data and returns to the main screen.

## Withdraw Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/WithdrawScene.png" alt="Econo Bank Withdraw Scene" width="400">
- Contains an input field for withdrawal amount.

## Deposit Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/DepositScene.png" alt="Econo Bank Deposit Scene" width="400">
- Includes an input field for deposit amount.

## Transfer Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/TransferScene.png" alt="Econo Bank Transfer Scene" width="400">
- Requires two fields: amount and recipient's email.

## Balance Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/BalanceScene.png" alt="Econo Bank Balance Scene" width="400">
- Shows total balance and three latest transactions.
- "More Transactions" button displays the full statement (scrollable).

## Account Information Page
<img src="BankAppFXMaven/src/main/resources/img/pagesPNG/AccountInfoScene.png" alt="Econo Bank Account Information Scene" width="400">
- Displays user information: email, account number, name, and surname.
- Only name, surname, and password fields are editable.

# Entity Relationship Diagram (ERD)
<img src="BankAppFXMaven/src/main/resources/img/Econo_Bank_ERD_Entity_Relationship_Diagram.png" alt="Econo Bank Entity Relationship Diagram ERD" width="600">

## Login (User Authentication):
- The Login entity represents user authentication.
- Attributes: login_id, user_id, password_hash, and last_login.
- The relationship between Login and User is one-to-one:
 - Each login corresponds to a single user, and vice versa.

## User:
- The User entity contains user information.
- Attributes: user_id, name, surname, and email (unique).
- The relationship between User and Bank Account is one-to-many:
 - Each user can have multiple bank accounts.

## Bank Account:
- The Bank Account entity represents individual accounts.
- Attributes: bank_acc_id, user_id, bank_acc_number, and bank_acc_balance.
- Relationships:
 - One-to-Many with Transfer:
  - A bank account can be associated with multiple transfers (both outgoing and incoming).
 - One-to-Many with Transaction:
  - A bank account can have multiple transactions (deposits, withdrawals).
 - One-to-One with Statement:
  - A Bank Account has one statement holding it's full history.

## Transfer:
- The Transfer records only account-to-account transactions.
- Attributes: transfer_id, from_bank_acc_id, to_bank_acc_id, transfer_amount, and transfer_date.
- Relationships:
 - Many-to-One with Bank Account:
  - Multiple transfers can be associated with one bank account (both outgoing and incoming).
 - Many-to-One with Transaction:
  - Multiple transactions contribute to one transfer.

## Transaction:
- The Transaction entity records all financial operations.
- Attributes: transaction_id, bank_acc_id, transaction_type, transaction_amount, and transaction_date.
- the 'transaction_type' is used to know if it's a withdraw, deposit or transfer.
- Relationships:
 - Many-to-One with Bank Account:
  - Multiple transactions can be made by one Bank Account.
 - One-to-Many with transfer:
  - A single transfer event encompasses multiple individual transactions. 
 - Many-to-One with statement:
  - Multiple transactions are shown in one statement (transaction history).

## Statement:
- The Statement entity provides transaction history.
- Attributes: statement_id, and bank_acc_id.
- It’s connected to Transaction, showing how transactions form statements.
- Relationships: 
 - One-to-Many with Transaction:
  - One Statement holds many transactions (transaction history).
 - One-to-One with Bank Account:
  - One Statement can be displayed regarding one Bank Account.
