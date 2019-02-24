package com.eccenca.gitlab.webapp.ancestry.controller;

import com.eccenca.gitlab.webapp.ancestry.cache.AncestryGraphCache;
import com.eccenca.gitlab.webapp.ancestry.dataobjects.AncestryNodeDO;
import com.eccenca.gitlab.webapp.ancestry.tool.AncestryTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("webapp/ancestry")
public class AncestryController {

    Logger logger = LoggerFactory.getLogger(AncestryController.class);
    /**
     *
     * @param nodes
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="createInputGraph", method = RequestMethod.POST ,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createInputGraph(@RequestBody List<AncestryNodeDO> nodes) throws Exception {

        final String METHOD_NAME = "AncestryController:createInputGraph";

        logger.info(METHOD_NAME + " Entering....");

        AncestryTool ancestryTool = AncestryTool.getInstance();
        ancestryTool.checkAncestryNodes(nodes);

        AncestryGraphCache.saveAncestorGraphInCache(nodes);

        return "Zero Parent Nodes: " + ancestryTool.getZeroParentsList()
                + " One Parent Nodes:" + ancestryTool.getOneParentList();

    }

    /**
     *
     * @param node
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="findCommonAncestors", method = RequestMethod.POST ,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean findCommonAncestors(@RequestBody AncestryNodeDO node) throws Exception {

        final String METHOD_NAME = "AncestryController:findCommonAncestors";

        logger.info(METHOD_NAME + " Entering....");

        boolean commonAncestorFound = false;

        if(AncestryGraphCache.getStoredGraph("ancestryGraph") != null){
            AncestryTool ancestryTool = AncestryTool.getInstance();
            commonAncestorFound = ancestryTool.inputPairHasCommonAncestors(node.getParentValue(),node.getChildValue(),
                    AncestryGraphCache.getStoredGraph("ancestryGraph"));
        }
        else{
            throw new Exception("Ancestry Graph needs to be created first");
        }

        return commonAncestorFound;
    }



}
