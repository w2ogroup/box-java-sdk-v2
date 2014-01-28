package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.resourcemanagers.BoxResourceManager;

public interface IResourceManagerPlugin {

    BoxResourceManager plugin(BoxClient client);
}
