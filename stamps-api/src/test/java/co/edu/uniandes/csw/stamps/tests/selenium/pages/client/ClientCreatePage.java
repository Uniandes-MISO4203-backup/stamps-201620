/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.stamps.tests.selenium.pages.client;

import co.edu.uniandes.csw.stamps.dtos.minimum.ClientDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientCreatePage {

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "telephone")
    private WebElement telephoneInput;

    @FindBy(id = "save-client")
    private WebElement saveBtn;

    @FindBy(id = "cancel-client")
    private WebElement cancelBtn;

    public void saveClient(ClientDTO client) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(client.getName());
       
         waitGui().until().element(addressInput).is().visible();
         addressInput.clear();
         addressInput.sendKeys(client.getAddress());
       
         waitGui().until().element(telephoneInput).is().visible();
         telephoneInput.clear();
         telephoneInput.sendKeys(client.getTelephone().toString());
       
         guardAjax(saveBtn).click();
    }
}
