package com.eccenca.gitlab.webapp.ancestry.cache;

import com.eccenca.gitlab.webapp.ancestry.dataobjects.AncestryNodeDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AncestryGraphCache {

    private static Map<String, List<AncestryNodeDO>> ancestryCache;

    public static void saveAncestorGraphInCache(List<AncestryNodeDO> ancestryGraph) throws Exception{

        if(ancestryCache == null)
            ancestryCache = new HashMap<String, List<AncestryNodeDO>>();

        ancestryCache.put("ancestryGraph", ancestryGraph);

    }

    public static List<AncestryNodeDO> getStoredGraph(String cacheId) {

        if(ancestryCache != null)
            return ancestryCache.get(cacheId);
        else
            return null;

    }
}
