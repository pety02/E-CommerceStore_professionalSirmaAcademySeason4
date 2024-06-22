## Classes, Interfaces and Enums Hirarchy
![model_interfaces_utils_hirarchy](https://github.com/pety02/E-CommerceStore_professionalSirmaAcademySeason4/assets/47276102/c8e4cb55-c767-4ab3-ba10-7fb48bfed01a)
## Main Classes
#### Main classes a re User, Order, Store and Application. They are the most significant part of the task. 
### User 
#### User class has unique id, nickname, and total money, given from all his debit cards and paypal wallets. 
### Store 
#### Store class contains unique id, name and items and their stocks as a map. 
### Order 
#### Order class has unique id, map of items and their quantity, user, payment type and payment IBAN.

### Enums
#### Enums are use to clasify different part of the program as subtypes of one thing for example PaymentType enum and its elements DEBIT_CAR and PAYPAL. Other enums are ElectonicsType (for different type of electronycs items) and Category enum (for defining if an item is INVENTORY, FRAGILE, ELECTRONICS or GROCERY). 

### Interfaces
#### Interfaces are used to determine what functionality is the most important for differnt group of classes. The interfaces are: Item, Sellable, Categorizable, Breakable, Perishable, PaymentTool and the template interfaces (Writebla and Readable, used to make the reader-writer classes for all different parts of the application in order to store the application's life cycle in files via objects serialization proccess).

## Model Classes
### AbstarctItem
#### AbstractItem is a public abstract class, parent of all items in the hirarchy.
### InventoryItem 
#### InventoryItem is the only child of AbstractItem that describes an inventory item.
### FragileItem
#### FragileItem is a class, a child of the InventoryItem that describes all fragile items in the store application.
### ElectronicsItem
#### ElectronicsItem is a subclass of the FragileItem that describes all electronics items as fragile ones in the store application.
### GroceryItem
#### GroceryItem is a class, a child of the InventoryItem that describes all grocery items in the store application.
### All ReaderWriters
## PaymentReaderWriter, InventoryReaderWriter, OrderReaderWriter, StoreReaderWriter and UserReaderWriter - write down the new changes in the program execution. 
### DebitCard
#### The debit card is a payment tool, described by unique id, IBAN, balance and owner.
### PaypalWallet
#### The paypal wallet is a payment tool, described by unique id, name of the waalet, IBAN, balance and owner.

## Command Hirarchy!
![commands_hirarchy](https://github.com/pety02/E-CommerceStore_professionalSirmaAcademySeason4/assets/47276102/ae043f5b-29ae-4b06-b0d0-f395338a2879)
#### There is a hirarchy of a base class (ParentCommand) and its child classes - DisplayItemsCommand, CategorizeItemsCommand, AddItemCommand, RemoveItemCommand, PlaceOrderCommand and ChoosePaymentTypeCommand. They also implements 4 different interfaces in dependance of their base task - StoreCommand, OrderCommand, TransactionCommand and PaymentTypeCommand.
### StoreCommand
#### It is an interface with only one function (execute). Its aim is to make different actions with store items and the customer's basket as adding or removing items in the customer's basket in the proccess of shopping.
### OrderCommand
#### It is an interface with only one function (execute). Its aim is to make orders.
### TransactionCommand
#### It is an interface with only one function (execute). Its aim is to execute transactions with different type of payment - debit_cart or paypal.
### PaymetnTypeCommand
#### It is an interface with only one function (execute). Its aim is to give the customer the opportunity to choose how to pay - debit_card or paypal.

## Application Example1:
![Example1](https://github.com/pety02/E-CommerceStore_professionalSirmaAcademySeason4/assets/47276102/016a4837-a144-46d4-bdf5-8fb6c9a92117)
#### Adding items in the basket then placing order. 

## Application Example2:
![Example2](https://github.com/pety02/E-CommerceStore_professionalSirmaAcademySeason4/assets/47276102/c5ed1683-f16a-4dc8-9ba1-a959e1a153cb)
#### Displaying and categorizing items of the store then trying to place order with an empty basket. 
