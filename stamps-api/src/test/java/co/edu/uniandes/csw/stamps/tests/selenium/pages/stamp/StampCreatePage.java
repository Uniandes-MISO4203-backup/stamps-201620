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
package co.edu.uniandes.csw.stamps.tests.selenium.pages.stamp;

import co.edu.uniandes.csw.stamps.dtos.minimum.StampDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StampCreatePage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "image")
    private WebElement imageInput;
    @FindBy(id = "price")
    private WebElement priceInput;
    @FindBy(id = "description")
    private WebElement descriptionInput;
    @FindBy(id = "artistComment")
    private WebElement artistCommentInput;  
    //Automatic Test
    @FindBy(id = "availableForSale")
    private WebElement availableForSaleInput;
    @FindBy(id = "qualification")
    private WebElement qualificationInput;
    @FindBy(id = "popularity")
    private WebElement popularityInput;
    @FindBy(id = "comments")
    private WebElement commentsInput;
    
    @FindBy(id = "save-stamp")
    private WebElement saveBtn;

    @FindBy(id = "cancel-stamp")
    private WebElement cancelBtn;

    public void saveStamp(StampDTO stamp) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(stamp.getName());
         waitGui().until().element(imageInput).is().visible();
         imageInput.clear();
         imageInput.sendKeys(stamp.getImage());
         waitGui().until().element(priceInput).is().visible();
         priceInput.clear();
         priceInput.sendKeys(stamp.getPrice().toString());
         
         //Test
         waitGui().until().element(descriptionInput).is().visible();
         descriptionInput.clear();
         descriptionInput.sendKeys(stamp.getDescription());         
         
         //Test
         waitGui().until().element(artistCommentInput).is().visible();
         artistCommentInput.clear();
         artistCommentInput.sendKeys(stamp.getArtistComment());         
                
        //Automatic Test
         waitGui().until().element(availableForSaleInput).is().visible();
         availableForSaleInput.clear();
         availableForSaleInput.sendKeys(String.valueOf(stamp.isAvailableForSale()));
                  
         waitGui().until().element(qualificationInput).is().visible();
         qualificationInput.clear();
         qualificationInput.sendKeys(String.valueOf(stamp.getQualification()));
        
         waitGui().until().element(popularityInput).is().visible();
         popularityInput.clear();
         popularityInput.sendKeys(stamp.getPopularity().toString());

         waitGui().until().element(commentsInput).is().visible();
         commentsInput.clear();
         commentsInput.sendKeys(stamp.getComments());
         
        guardAjax(saveBtn).click();
    }
}
