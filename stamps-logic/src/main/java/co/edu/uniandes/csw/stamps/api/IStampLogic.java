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
package co.edu.uniandes.csw.stamps.api;

import co.edu.uniandes.csw.stamps.entities.StampEntity;
import co.edu.uniandes.csw.stamps.entities.CategoryEntity;
import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import java.util.List;

public interface IStampLogic {
    public int countStamps();
    public List<StampEntity> getStamps(Long artistid);
    public List<StampEntity> getStamps(Integer page, Integer maxRecords, Long artistid);
    public List<StampEntity> getStampByCategory(Integer page, Integer maxRecords, Long categoryid);
    public StampEntity getStamp(Long stampid);
    public StampEntity createStamp(Long artistid, StampEntity entity);
    public StampEntity updateStamp(Long artistid, StampEntity entity);
    public void deleteStamp(Long id);
    public List<CategoryEntity> listCategory(Long stampId);
    public CategoryEntity getCategory(Long stampId, Long categoryId);
    public CategoryEntity addCategory(Long stampId, Long categoryId);
    public List<CategoryEntity> replaceCategory(Long stampId, List<CategoryEntity> list);
    public void removeCategory(Long stampId, Long categoryId);
    public List<StampEntity> getStampsAll();
    public List<StampEntity> getHighlighted();
    public List<StampEntity> getLatest();
    //Relación con StampCommentary
    public List<StampCommentaryEntity> listStampCommentaries(Long stampid);
    public StampCommentaryEntity getStampCommentary(Long stampid, Long stampcommentaryid);
    public StampCommentaryEntity addStampCommentary(Long stampid, Long stampcommentaryid);
    public List<StampCommentaryEntity> replaceStampCommentaries(Long stampid, List<StampCommentaryEntity> list);
    public void removeStampCommentary(Long stampid, Long stampcommentaryid);
}
