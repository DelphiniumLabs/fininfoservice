package com.goit.fininfoservice.telegram.keyboards.buttons.factory;

import com.goit.fininfoservice.telegram.keyboards.buttons.Pressable;
///
/// Abstract Button factory
public  abstract class ButtonCreator {
   protected abstract Pressable create();

}

