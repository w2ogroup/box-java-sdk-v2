package com.box.boxjavalibv2.jsonentities;

import com.box.boxjavalibv2.dao.BoxBase;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxJSONStringEntity;

/**
 * Default implementation of the {@link IBoxJSONStringEntity}, this class utilizes {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>} to
 * convert object into JSON String.
 */
public class DefaultJSONStringEntity extends BoxBase implements IBoxJSONStringEntity {

    @Override
    public String toJSONString(IBoxJSONParser parser) throws BoxJSONException {
        return parser.convertToString(this);
    }

}
