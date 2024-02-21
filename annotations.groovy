// Annotations manipulations

// Remove child annotations and keep only root annotations
top_annotations = getCurrentHierarchy().getRootObject().getChildObjects().findAll {it.isAnnotation()}
child_annotations = getAnnotationObjects().findAll{ (! (it in top_annotations)) }
print("Removing: " + child_annotations )
removeObjects(child_annotations , false)

// Mark top annotations as "cell"
top_annotations.forEach { p -> p.setPathClass(getPathClass("cell"));  print(p)}

// Create child "cytoplasm" annotations
selectObjects(top_annotations)
runPlugin('qupath.lib.plugins.objects.DilateAnnotationPlugin', '{"radiusPixels":-25.0,"lineCap":"ROUND","removeInterior":false,"constrainToParent":true}')
top_annotations.forEach { top ->
    top.getChildObjects().forEach {  child ->
        if (child.getPathClass() == getPathClass("cell")) { // child inherits parent's pathClass
            child.setPathClass(getPathClass("cytoplasm"))
            child.setLocked(true);
            fireHierarchyUpdate()
            }
    } 
}
