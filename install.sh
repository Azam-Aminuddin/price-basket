#!/bin/bash
unzip PriceBasket.zip
chmod +x ./PriceBasket/PriceBasket
if !(hash java); then
	sudo apt-get install default-jre
fi
