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
    public List<StampCommentaryEntity> getStampCommentaries();
    public List<StampCommentaryEntity> getStampCommentaries(Long stampId);
    public List<StampCommentaryEntity> getStampCommentaries(Integer page, Integer maxRecords, Long stampId);
    public StampCommentaryEntity getStampCommentary(Long stampcommentaryid);
    public StampCommentaryEntity createStampCommentary(Long stampId, StampCommentaryEntity entity);
    public StampCommentaryEntity updateStampCommentary(Long stampId, StampCommentaryEntity entity);
    public void deleteStampCommentary(Long stampCommentaryId);
}
