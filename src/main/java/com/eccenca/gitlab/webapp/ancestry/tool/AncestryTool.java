package com.eccenca.gitlab.webapp.ancestry.tool;

import com.eccenca.gitlab.webapp.ancestry.dataobjects.AncestryNodeDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AncestryTool {

    Logger logger = LoggerFactory.getLogger(AncestryTool.class);

    private List<Integer> parentNodesList = new ArrayList<Integer>();
    private List<Integer> childNodesList = new ArrayList<Integer>();
    private Map <Integer,Integer> parentChildMap = new HashMap <Integer,Integer>();

    private List<Integer> zeroParentsList = new ArrayList<Integer>();
    private List<Integer> oneParentList = new ArrayList<Integer>();

    private static final AncestryTool instance = new AncestryTool();

    public static AncestryTool getInstance(){
        return instance;
    }

    /**
     *
     * @param firstTarget
     * @param secondTarget
     * @param nodes
     * @return
     */
    public boolean inputPairHasCommonAncestors(int firstTarget, int secondTarget, List<AncestryNodeDO> nodes){

        final String METHOD_NAME = "AncestryTool:findAllAncestors";

        logger.info(METHOD_NAME + " Entering with targets:" + firstTarget + "," +   secondTarget);

        boolean commonAncestorFound = false;

        List <Integer> firstIndividualAncestors = findAllAncestors(nodes, firstTarget, null);
        System.out.println("firstIndividualAncestors: " + firstIndividualAncestors);

        List <Integer> secondIndividualAncestors = findAllAncestors(nodes, secondTarget, null);
        System.out.println("secondIndividualAncestors: " + secondIndividualAncestors);

        for(int i = 0 ; i < firstIndividualAncestors.size(); i++){
            if(secondIndividualAncestors != null && secondIndividualAncestors.size() > 0){
                if(secondIndividualAncestors.contains(firstIndividualAncestors.get(i))){
                    commonAncestorFound = true;
                    break;
                }
            }
        }

        return commonAncestorFound;
    }

    /**
     *
     * @param nodes
     * @param target
     * @param targetAncestors
     * @return
     */
    public List<Integer> findAllAncestors(List<AncestryNodeDO> nodes, int target, List<Integer> targetAncestors) {

        final String METHOD_NAME = "AncestryTool:findAllAncestors";

        logger.info(METHOD_NAME + " Entering with nodes:" + nodes + " target:" + target + " targetAncestors:" + targetAncestors );

        int nextSearchValue = 0;

        if(targetAncestors == null)
            targetAncestors = new ArrayList<Integer>();

        for(int i = 0; i < nodes.size(); i++) {

            if(nodes.get(i).getChildValue() == target) {

                targetAncestors.add(nodes.get(i).getParentValue());

                nextSearchValue = nodes.get(i).getParentValue();

                if(nextSearchValue == 0)
                    return targetAncestors;

                findAllAncestors(nodes, nextSearchValue, targetAncestors);

            }
        }

        return targetAncestors;
    }
    /**
     *
     * @param nodes
     */
    public void checkAncestryNodes(List<AncestryNodeDO> nodes) {

        final String METHOD_NAME = "AncestryTool:checkAncestryNodes";

        logger.info(METHOD_NAME + " Entering with nodes:" + nodes);

        parentNodesList.clear();
        childNodesList.clear();
        parentChildMap.clear();
        zeroParentsList.clear();
        oneParentList.clear();

        for(int i = 0; i < nodes.size(); i++) {

            parentNodesList.add(nodes.get(i).getParentValue());
            childNodesList.add(nodes.get(i).getChildValue());

            if(parentChildMap.containsKey(nodes.get(i).getChildValue())) {
                parentChildMap.put(nodes.get(i).getChildValue(), parentChildMap.get(nodes.get(i).getChildValue()) + 1);
            }
            else {
                parentChildMap.put(nodes.get(i).getChildValue(), 1);
            }

        }

        //Validate Input
        validateParentChildPairs();

        getOneParent();
        getZeroParents();

        logger.info(METHOD_NAME + " Leaving method..." );

    }

    /**
     *
     * @return List<Integer>
     */
    public List<Integer> getZeroParents(){

        final String METHOD_NAME = "AncestryTool:getZeroParents";

        logger.info(METHOD_NAME + " Entering.....");

        for(int i = 0; i < parentNodesList.size(); i++) {

            //Zero Parents
            if(childNodesList.contains(parentNodesList.get(i))){
                continue;
            }
            else {
                if(!zeroParentsList.contains(parentNodesList.get(i)))
                    zeroParentsList.add(parentNodesList.get(i));
            }


        }

        logger.info(METHOD_NAME + " Zero Parent List:" + zeroParentsList);

        return zeroParentsList;
    }

    /**
     *
     * @return List<Integer>
     */
    public List<Integer> getOneParent(){

        final String METHOD_NAME = "AncestryTool:getOneParent";

        logger.info(METHOD_NAME + " Entering.....");

        for(int i = 0; i < parentNodesList.size(); i++) {

            //One Parent
            if(parentChildMap.get(childNodesList.get(i)) == 1) {
                oneParentList.add(childNodesList.get(i));
            }

        }

        logger.info(METHOD_NAME + " Zero Parent List:" + oneParentList);

        return oneParentList;
    }

    private void validateParentChildPairs() throws IllegalArgumentException{

        final String METHOD_NAME = "AncestryTool:validateParentChildPairs";

        logger.info(METHOD_NAME + " Entering.....");

        for(int i = 0; i < parentNodesList.size(); i++) {

            if(parentChildMap.get(childNodesList.get(i)) > 2) {
                throw new IllegalArgumentException("One child node has more than 2 parents");
            }

        }
    }


    /**
     * @return the parentNodesList
     */
    public List<Integer> getParentNodesList() {
        return parentNodesList;
    }

    /**
     * @param parentNodesList the parentNodesList to set
     */
    public void setParentNodesList(List<Integer> parentNodesList) {
        this.parentNodesList = parentNodesList;
    }

    /**
     * @return the childNodesList
     */
    public List<Integer> getChildNodesList() {
        return childNodesList;
    }

    /**
     * @param childNodesList the childNodesList to set
     */
    public void setChildNodesList(List<Integer> childNodesList) {
        this.childNodesList = childNodesList;
    }

    /**
     * @return the parentChildMap
     */
    public Map<Integer, Integer> getParentChildMap() {
        return parentChildMap;
    }

    /**
     * @param parentChildMap the parentChildMap to set
     */
    public void setParentChildMap(Map<Integer, Integer> parentChildMap) {
        this.parentChildMap = parentChildMap;
    }

    /**
     * @return the zeroParentsList
     */
    public List<Integer> getZeroParentsList() {
        return zeroParentsList;
    }

    /**
     * @param zeroParentsList the zeroParentsList to set
     */
    public void setZeroParentsList(List<Integer> zeroParentsList) {
        this.zeroParentsList = zeroParentsList;
    }

    /**
     * @return the oneParentList
     */
    public List<Integer> getOneParentList() {
        return oneParentList;
    }

    /**
     * @param oneParentList the oneParentList to set
     */
    public void setOneParentList(List<Integer> oneParentList) {
        this.oneParentList = oneParentList;
    }


  }
