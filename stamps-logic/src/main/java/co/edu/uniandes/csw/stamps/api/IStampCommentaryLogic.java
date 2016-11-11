/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.api;

import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import java.util.List;
/**
 *
 * @author ga.chica10
 */
public interface IStampCommentaryLogic {
    public int countStampCommentaries();
    public List<StampCommentaryEntity> getStampCommentaries(Long stampid);
    public List<StampCommentaryEntity> getStampCommentaries(Integer page, Integer maxRecords, Long stampid);
    public StampCommentaryEntity getStampCommentary(Long stampcommentaryid);
    public StampCommentaryEntity createStampCommentary(Long stampid, StampCommentaryEntity entity);
    public StampCommentaryEntity updateStampCommentary(Long stampid, StampCommentaryEntity entity);
    public void deleteStampCommentary(Long stampcommentaryid);
}
