package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.MAIN)
public class WTeam extends DmModel {

    private static final long serialVersionUID = 7658868637104001981L;

    private Integer id;

    private String name;

    private String declaration;

    private String description;

    private String board;

    private String logo;

    private Integer size;

    private Integer kill;

    private Integer headShot;

    private Integer gameWin;

    private Integer gameTotal;

    private Integer challengeWin;

    private Integer challengeTotal;

    private Date createdTime;

    private String deleted;

    private Integer score;

    private Integer hitScore;

    private String province;

    private String city;

    private Integer rank;

    private Integer level;

    private Integer exp;

    private Integer recoreRankingFormer;

    private Integer recoreRankingCurr;

    private Integer fightRankingFormer;

    private Integer fightRankingCurr;

    private Integer preweekResAmount;

    private Integer teamSpaceLevel;

    private Integer unusableResource;

    private Integer usableResource;

    private Integer teamSpaceActive;

    private Date tResLastAddTime;

    private Date tResourceBeginTime;

    private Integer lastTeamPlaceLevelUpTime;

    private Integer predayResAmount;

    private Integer curWeekRobCount;

    private Date curWeekRobUpdateTime;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration == null ? null : declaration.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board == null ? null : board.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getKill() {
        return kill;
    }

    public void setKill(Integer kill) {
        this.kill = kill;
    }

    public Integer getHeadShot() {
        return headShot;
    }

    public void setHeadShot(Integer headShot) {
        this.headShot = headShot;
    }

    public Integer getGameWin() {
        return gameWin;
    }

    public void setGameWin(Integer gameWin) {
        this.gameWin = gameWin;
    }

    public Integer getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(Integer gameTotal) {
        this.gameTotal = gameTotal;
    }

    public Integer getChallengeWin() {
        return challengeWin;
    }

    public void setChallengeWin(Integer challengeWin) {
        this.challengeWin = challengeWin;
    }

    public Integer getChallengeTotal() {
        return challengeTotal;
    }

    public void setChallengeTotal(Integer challengeTotal) {
        this.challengeTotal = challengeTotal;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHitScore() {
        return hitScore;
    }

    public void setHitScore(Integer hitScore) {
        this.hitScore = hitScore;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getRecoreRankingFormer() {
        return recoreRankingFormer;
    }

    public void setRecoreRankingFormer(Integer recoreRankingFormer) {
        this.recoreRankingFormer = recoreRankingFormer;
    }

    public Integer getRecoreRankingCurr() {
        return recoreRankingCurr;
    }

    public void setRecoreRankingCurr(Integer recoreRankingCurr) {
        this.recoreRankingCurr = recoreRankingCurr;
    }

    public Integer getFightRankingFormer() {
        return fightRankingFormer;
    }

    public void setFightRankingFormer(Integer fightRankingFormer) {
        this.fightRankingFormer = fightRankingFormer;
    }

    public Integer getFightRankingCurr() {
        return fightRankingCurr;
    }

    public void setFightRankingCurr(Integer fightRankingCurr) {
        this.fightRankingCurr = fightRankingCurr;
    }

    public Integer getPreweekResAmount() {
        return preweekResAmount;
    }

    public void setPreweekResAmount(Integer preweekResAmount) {
        this.preweekResAmount = preweekResAmount;
    }

    public Integer getTeamSpaceLevel() {
        return teamSpaceLevel;
    }

    public void setTeamSpaceLevel(Integer teamSpaceLevel) {
        this.teamSpaceLevel = teamSpaceLevel;
    }

    public Integer getUnusableResource() {
        return unusableResource;
    }

    public void setUnusableResource(Integer unusableResource) {
        this.unusableResource = unusableResource;
    }

    public Integer getUsableResource() {
        return usableResource;
    }

    public void setUsableResource(Integer usableResource) {
        this.usableResource = usableResource;
    }

    public Integer getTeamSpaceActive() {
        return teamSpaceActive;
    }

    public void setTeamSpaceActive(Integer teamSpaceActive) {
        this.teamSpaceActive = teamSpaceActive;
    }

    public Date gettResLastAddTime() {
        return tResLastAddTime;
    }

    public void settResLastAddTime(Date tResLastAddTime) {
        this.tResLastAddTime = tResLastAddTime;
    }

    public Date gettResourceBeginTime() {
        return tResourceBeginTime;
    }

    public void settResourceBeginTime(Date tResourceBeginTime) {
        this.tResourceBeginTime = tResourceBeginTime;
    }

    public Integer getLastTeamPlaceLevelUpTime() {
        return lastTeamPlaceLevelUpTime;
    }

    public void setLastTeamPlaceLevelUpTime(Integer lastTeamPlaceLevelUpTime) {
        this.lastTeamPlaceLevelUpTime = lastTeamPlaceLevelUpTime;
    }

    public Integer getPredayResAmount() {
        return predayResAmount;
    }

    public void setPredayResAmount(Integer predayResAmount) {
        this.predayResAmount = predayResAmount;
    }

    public Integer getCurWeekRobCount() {
        return curWeekRobCount;
    }

    public void setCurWeekRobCount(Integer curWeekRobCount) {
        this.curWeekRobCount = curWeekRobCount;
    }

    public Date getCurWeekRobUpdateTime() {
        return curWeekRobUpdateTime;
    }

    public void setCurWeekRobUpdateTime(Date curWeekRobUpdateTime) {
        this.curWeekRobUpdateTime = curWeekRobUpdateTime;
    }

    @Override
    public boolean getIsRemoved() {
        return "Y".equals(deleted);
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        deleted = isRemoved ? "Y" : "N";
    }
}