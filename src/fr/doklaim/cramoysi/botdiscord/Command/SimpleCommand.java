/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command;

import fr.doklaim.cramoysi.botdiscord.Command.Command.ExecutorType;
import java.lang.reflect.Method;

/**
 *
 * @author CRamoysi
 */
public final class SimpleCommand {
    private final String name, description;
    private final ExecutorType executorType;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String description, ExecutorType executorType, Object object, Method method) {
        super();
        this.name = name;
        this.description = description;
        this.executorType = executorType;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ExecutorType getExecutorType() {
        return executorType;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
    
    
    
}
