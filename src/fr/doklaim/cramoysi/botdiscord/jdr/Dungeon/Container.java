/*
 * Copyright 2018 CRamoysi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.doklaim.cramoysi.botdiscord.jdr.Dungeon;

import fr.doklaim.cramoysi.botdiscord.jdr.character.Inventory;

/**
 * Dans chaque salle il peut y avoir des @Container contenant des @Item. Il s'agit par exemple, de coffres, d'armoir, etc.
 * @author CRamoysi
 */
public class Container {
    
    public String name; //exemple "armoire", "coffre", "sac"
    public Inventory items;


    
}
