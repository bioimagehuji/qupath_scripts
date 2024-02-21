guiscript=true

def classes = getQuPath().getAvailablePathClasses().findAll {it -> it}  // copy list of available classes to avoid triggering JavaFX
if (!(getPathClass("cytoplasm") in classes)) {
    classes.add(getPathClass("new_class1"))
}    
if (!(getPathClass("membrane") in classes)) {
    classes.add(getPathClass("new_class2"))
}    
getQuPath().getAvailablePathClasses().setAll(classes)  // update gui
print(getQuPath().getAvailablePathClasses())
