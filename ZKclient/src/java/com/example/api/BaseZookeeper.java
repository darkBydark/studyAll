package com.example.api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class BaseZookeeper implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
