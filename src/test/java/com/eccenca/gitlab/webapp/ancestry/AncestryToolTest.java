package com.eccenca.gitlab.webapp.ancestry;

import com.eccenca.gitlab.webapp.ancestry.dataobjects.AncestryNodeDO;
import com.eccenca.gitlab.webapp.ancestry.tool.AncestryTool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AncestryToolTest {

    @Test
    public void checkAncestryNodes_dataCase1_successfull() throws Exception{

        List<AncestryNodeDO> nodes = new ArrayList<AncestryNodeDO>();
        nodes.add(new AncestryNodeDO(10,3));
        nodes.add(new AncestryNodeDO(2,3));
        nodes.add(new AncestryNodeDO(3,6));
        nodes.add(new AncestryNodeDO(5,6));
        nodes.add(new AncestryNodeDO(5,17));
        nodes.add(new AncestryNodeDO(4,5));
        nodes.add(new AncestryNodeDO(4,8));
        nodes.add(new AncestryNodeDO(8,9));

        List<Integer> expectedOneParentList = new ArrayList<Integer>();
        expectedOneParentList.add(17);
        expectedOneParentList.add(5);
        expectedOneParentList.add(8);
        expectedOneParentList.add(9);

        List <Integer> expectedZeroParentList = new ArrayList<Integer>();
        expectedZeroParentList.add(10);
        expectedZeroParentList.add(2);
        expectedZeroParentList.add(4);

        AncestryTool ancestryApp = new AncestryTool();
        ancestryApp.checkAncestryNodes(nodes);

        assertTrue(ancestryApp.getOneParentList().containsAll(expectedOneParentList));
        assertTrue(ancestryApp.getZeroParentsList().containsAll(expectedZeroParentList));
    }

    @Test (expected  = IllegalArgumentException.class)
    public void checkAncestryNodes_excessParents_fail() throws Exception{

        List<AncestryNodeDO> nodes = new ArrayList<AncestryNodeDO>();
        nodes.add(new AncestryNodeDO(10,3));
        nodes.add(new AncestryNodeDO(2,3));
        nodes.add(new AncestryNodeDO(11,3));
        nodes.add(new AncestryNodeDO(3,6));
        nodes.add(new AncestryNodeDO(5,6));
        nodes.add(new AncestryNodeDO(5,17));
        nodes.add(new AncestryNodeDO(4,5));
        nodes.add(new AncestryNodeDO(4,8));
        nodes.add(new AncestryNodeDO(8,9));


        AncestryTool ancestryApp = new AncestryTool();
        ancestryApp.checkAncestryNodes(nodes);
    }

    @Test
    public void checkCommonAncestors_dataCase1_fail() throws Exception{

        List<AncestryNodeDO> nodes = new ArrayList<AncestryNodeDO>();
        nodes.add(new AncestryNodeDO(10,3));
        nodes.add(new AncestryNodeDO(2,3));
        nodes.add(new AncestryNodeDO(3,6));
        nodes.add(new AncestryNodeDO(5,6));
        nodes.add(new AncestryNodeDO(5,17));
        nodes.add(new AncestryNodeDO(4,5));
        nodes.add(new AncestryNodeDO(4,8));
        nodes.add(new AncestryNodeDO(8,9));

        AncestryTool ancestryApp = new AncestryTool();
        ancestryApp.checkAncestryNodes(nodes);

        assertFalse(ancestryApp.inputPairHasCommonAncestors(3,8, nodes));

    }

    @Test
    public void checkCommonAncestors_dataCase2_sucess() throws Exception{

        List<AncestryNodeDO> nodes = new ArrayList<AncestryNodeDO>();
        nodes.add(new AncestryNodeDO(10,3));
        nodes.add(new AncestryNodeDO(2,3));
        nodes.add(new AncestryNodeDO(3,6));
        nodes.add(new AncestryNodeDO(5,6));
        nodes.add(new AncestryNodeDO(5,17));
        nodes.add(new AncestryNodeDO(4,5));
        nodes.add(new AncestryNodeDO(4,8));
        nodes.add(new AncestryNodeDO(8,9));

        AncestryTool ancestryApp = new AncestryTool();
        ancestryApp.checkAncestryNodes(nodes);

        assertTrue(ancestryApp.inputPairHasCommonAncestors(5,8, nodes));

    }

    @Test
    public void checkCommonAncestors_dataCase3_success() throws Exception{

        List<AncestryNodeDO> nodes = new ArrayList<AncestryNodeDO>();
        nodes.add(new AncestryNodeDO(10,3));
        nodes.add(new AncestryNodeDO(2,3));
        nodes.add(new AncestryNodeDO(3,6));
        nodes.add(new AncestryNodeDO(5,6));
        nodes.add(new AncestryNodeDO(5,17));
        nodes.add(new AncestryNodeDO(4,5));
        nodes.add(new AncestryNodeDO(4,8));
        nodes.add(new AncestryNodeDO(8,9));

        AncestryTool ancestryApp = new AncestryTool();
        ancestryApp.checkAncestryNodes(nodes);

        assertTrue(ancestryApp.inputPairHasCommonAncestors(6,8, nodes));

    }


}
