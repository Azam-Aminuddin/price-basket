# Price-Basket

This is a java console application to price a basket of goods and taking into account some special offers. 
This application have following features:
1. Can configure available shopping items.
1. Can configure available special offers.
1. Can handle special offer of Discount. For example, Apples have a 10% discount off their normal price this week.
1. Can handle special offer of Buy some items and get discount on other item. For example, Buy 2 tins of soup and get a loaf of bread for half price.
1. Can configure format display of currency and its cents.
1. Can configure text output.
1. Can configure location of conf file.  

### Execution
This application are executed via command prompt/terminal command with following syntax:  
<pre>PriceBasket Item1 Item2 Item3 ... ItemN</pre> 
An item name could appear once or more.

Default conf file is **conf/store.conf**.We could configure location of conf file with following syntax:
<pre>PriceBasket conf=path-to-conf Item1 Item2 Item3 ... ItemN</pre>

### Output
Output to the console:
1. Subtotal
1. Special offer discounts if applicable
1. Total

### 1. Sample usecase
Available shopping items are:
- Soup – 65p per tin 
- Bread – 80p per loaf 
- Milk – £1.30 per bottle 
- Apples – £1.00 per bag 

Available offers are: 
- Apples have a 10% discount off their normal price this week 
- Buy 2 tins of soup and get a loaf of bread for half price 
 
#### Store.conf
The conf file contains following:
<pre>
# Items
itemCount=4
item.1=Soup,0.65
item.2=Bread,0.80
item.3=Milk,1.30
item.4=Apples,1.00

# Offers
offerCount=2
offer.1=discount,10%,Apples,0.1
offer.2=otherItemDiscount,Buy2GetHalf,Soup,2,Bread,0.5</pre>

#### Sample execution
<pre>PriceBasket Apples Milk Bread</pre> 
 
Output to the console:  
<pre>Subtotal: £3.10  
Apples 10% off: 10p  
Total: £3.00</pre> 
 
### 2. Case of no special offers
If no special offers are applicable then output to the console:  
<pre>Subtotal: £1.30 (No offers available)  
Total price: £1.30</pre> 

## How to install?
Assume you already have following files:
<pre>
install.sh
PriceBasket.zip
README.md           // this file</pre>

In osx or linux environment use following command to install:  
<pre>sudo chmod +x ./install.sh
sudo ./install.sh</pre>

It will expand **PriceBasket.zip** and output following directory stucture:
<pre>PriceBasket
    +-- conf                     
        +-- store.conf    
    +-- src                         // source folder
        +-- main   
        +-- test   
    +-- target
        +-- PacketBacket.jar    
    +--- PacketBacket               // executable script
    +--- pom.xml                    // required file to build from source
    </pre>

Then cd to the directory PriceBasket, and this application is ready to use.
  
## How to build from source?
To build from source you could use Apache Maven. Use following command from folder PriceBasket.
<pre>mvn clean package</pre>

## Github Repository
For latest version you could get it from:
https://github.com/Azam-Aminuddin/price-basket

