
//levagja a 0 - at a sztring elejerol es integert ad vissza
function getIntegerFromString( str, startPosition, endPosition )
{
	if( parseInt(str.toString().substring(parseInt(startPosition),parseInt(startPosition)+1)) == "0" )
	{
		return parseInt(str.toString().substring(parseInt(startPosition)+1,parseInt(endPosition)));
	} 
	else return parseInt(str.toString().substring(parseInt(startPosition),parseInt(endPosition)));
}

//from <? to
function checkDateField( from, to )
{
	if( parseInt(from.toString().substring(0,4)) == parseInt(to.toString().substring(0,4)) )
	{
		if( getIntegerFromString(from, 5, 7) == getIntegerFromString(to, 5, 7) )
		{
			if( getIntegerFromString(from, 8, 10) == getIntegerFromString(to, 8, 10) )
			{
				return true;
			}
			else if( getIntegerFromString(from, 8, 10) < getIntegerFromString(to, 8, 10) )
			{
				return true;
			}
			else return false;
		}
		else if( getIntegerFromString(from, 5, 7) < getIntegerFromString(to, 5, 7) )
		{
			return true;
		}
		else return false;
	}
	else if( parseInt(from.toString().substring(0,4)) < parseInt(to.toString().substring(0,4)) )
	{
		return true;
	}
	else return false;
}
//
function isInteger(value) 
{
	var digits="1234567890";
	for (var i=0; i < value.length; i++) 
	{
		if (digits.indexOf(value.charAt(i))==-1) { return false; }
	}
	return true;
} 

//utility - k

//elrejt - megjelenit egy divet
function showOtherDatas( objectId )
{
	if(	document.getElementById(objectId).style.display == "block" )
	{
		document.getElementById(objectId).style.display = "none";
	}
	else 
	{
		document.getElementById(objectId).style.display = "block";
	}
}

//kitorli a text mezot
function clearTextFieldById(inputTextElementId)
{
	document.getElementById(inputTextElementId).value = "";
}

//uj tabot nyit meg a parameterek alapjan
function openTab( tabCaption, target )
{ 
	if ( target != null )
	{
		var wc = top.parent.browserFrame.webUIController;
		wc.loadDocument('',tabCaption, target);
		return false;
	}
	return false;
}

//kijelolt radiobuttonok szamat adja vissza
function countSelectedRadio()
{
	count = 0;
	var radios = document.getElementsByName("radio");
	for(i=0;i<radios.length;i++)
	{
		if(radios[i].checked)
		{
			count++;
		}
	}
	return count;
}

//a kivalosztott radiobutton indexet adja vissza
function getSelectedRadioIndexByName( radioName )
{
	var radios = document.getElementsByName(radioName);
	for(i=0;i<radios.length;i++)
	{
		if(radios[i].checked)
		{
			return i;
		}
	}
	return -1;
}

//kijelolt checkboxok szamat adja vissza
function countSelectedItemsByName(name)
{
	count = 0;
	var items = document.getElementsByName(name);
	for(i=0;i<items.length;i++)
	{
		if(items[i].checked)
		{
			count++;
		}
	}
	return count;
}

//uzenet konvert?l?s
function convertMessage(message, sep)
{
	var msg = "Hiba:\n";
	var str = message;
	var index = str.toString().indexOf(sep);
	while( index != -1 )
	{
		msg += str.toString().substring(0,index)+"\n";
		str = str.toString().substring(index+1);
		index = str.indexOf(sep);
	}
	return msg;
}
//uzenet megjelenites
function showMessage(message)
{
	if( ( message != null ) &&
		( message != "" ) )
	{
		alert(convertMessage(message, '#'));		
	}
}

//lista validalas
function validListSelection()
{
	if(countSelectedRadio() == 0) 
	{
		alert("Nincs kijeloles!")
		return false;
	}
	return true;
}

function showPointer()
{
	var tdId = document.getElementById("orderby").value;
	var tdObj = document.getElementById(tdId);
	tdObj.style.backgroundPositionX = "right";
	tdObj.style.backgroundRepeat = "no-repeat";
	
	if(	document.getElementById("order").value == "asc" )
	{
		tdObj.style.backgroundImage = "url(./images/down_arrow.jpg)";
	}
	else
	{
		tdObj.style.backgroundImage = "url(./images/up_arrow.jpg)";
	}
}

function closeIt()
{
	var wc = parent.top.browserFrame.webUIController;
	var documentSelector = wc.getDocumentManager().getDocumentSelector();
	if (documentSelector) documentSelector.onCloseClick();
//		var command = new CloseActiveDocumentCommand();
//		command.execute();
}

function fillFakeText() {
	document.getElementById("fakeFileName").value=document.getElementById("fileToUpload").value;
}

function setUpForSubmit(action)
{
	document.getElementsByName("action")[0].value = action;
}

function putItems(clientSelectName, supplierSelectName, clientButtonName, supplierButtonName)
{
	client = document.getElementsByName(clientSelectName)[0];
	supplier = document.getElementsByName(supplierSelectName)[0];
	clientSelectedIndex = client.selectedIndex;
	if(clientSelectedIndex != -1)
	{
		clientSelectedOption = client.options[clientSelectedIndex];
		supplier.add(new Option(clientSelectedOption.text, clientSelectedOption.value));
		client.remove(clientSelectedIndex);
		if( client.options.length == 0 )
		{
			document.getElementsByName(clientButtonName)[0].disabled = true;
		}
		else
		{
			document.getElementsByName(clientButtonName)[0].disabled = false;
		}
		document.getElementsByName(supplierButtonName)[0].disabled = false
	}
}

function initButtons()
{
	if( document.getElementsByName("select")[0].options.length == 0 )
	{
		document.getElementsByName("toRight")[0].disabled = true;
	}
	if( document.getElementsByName("select2")[0].options.length == 0 )
	{
		document.getElementsByName("toLeft")[0].disabled = true;
	}
}

function removeSelectOptions(elementName)
{
	var element = document.getElementsByName(elementName)[0];
   	for( i=element.length-1;i>=0; i--) 
   	{
		element.remove(i);
	}
}