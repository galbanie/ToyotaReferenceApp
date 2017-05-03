@file:Suppress("unused")

package com.github.galbanie.models

import tornadofx.*
import javafx.beans.property.ObjectProperty

/**
 * Created by Galbanie on 2017-04-14.
 */

class Reference(id : String, make : String, model : String, year : String, chassisCode : String, other : String, engineLiter : String, aspiration : String, driveType : String, numDoor : String, bodyType : String,
                fuelType : String, subModel: String, engineCode : String, comment : String) {

    var id by property(id)
    fun idProperty() = getProperty(Reference::id)

    var make by property(make)
    fun makeProperty() = getProperty(Reference::make)

    var model by property(model)
    fun modelProperty() = getProperty(Reference::model)

    var year by property(year)
    fun yearProperty() = getProperty(Reference::year)

    var chassisCode by property(chassisCode)
    fun chassisCodeProperty() = getProperty(Reference::chassisCode)

    var other by property(other)
    fun otherProperty() = getProperty(Reference::other)

    var engineLiter by property(engineLiter)
    fun engineLiterProperty() = getProperty(Reference::engineLiter)

    var aspiration by property(aspiration)
    fun aspirationProperty() = getProperty(Reference::aspiration)

    var driveType by property(driveType)
    fun driveTypeProperty() = getProperty(Reference::driveType)

    var numDoor by property(numDoor)
    fun numDoorProperty() = getProperty(Reference::numDoor)

    var bodyType by property(bodyType)
    fun bodyTypeProperty() = getProperty(Reference::bodyType)

    var fuelType by property(fuelType)
    fun fuelTypeProperty() = getProperty(Reference::fuelType)

    var submodel by property(subModel)
    fun submodelProperty() = getProperty(Reference::submodel)

    var engineCode by property(engineCode)
    fun engineCodeProperty() = getProperty(Reference::engineCode)

    var comment by property(comment)
    fun commentProperty() = getProperty(Reference::comment)

    fun matches(query : String) : Boolean {

        return make.toLowerCase().contains(query.toLowerCase().toRegex()) || model.toLowerCase().contains(query.toLowerCase().toRegex()) || year.toLowerCase().contains(query.toLowerCase().toRegex())
                || chassisCode.toLowerCase().contains(query.toLowerCase().toRegex()) || engineLiter.toLowerCase().contains(query.toLowerCase().toRegex()) || engineCode.toLowerCase().contains(query.toLowerCase().toRegex())
                || aspiration.toLowerCase().contains(query.toLowerCase().toRegex()) || driveType.toLowerCase().contains(query.toLowerCase().toRegex()) || bodyType.toLowerCase().contains(query.toLowerCase().toRegex()) || numDoor.toLowerCase().contains(query.toLowerCase().toRegex())
                || fuelType.toLowerCase().contains(query.toLowerCase().toRegex()) || submodel.toLowerCase().contains(query.toLowerCase().toRegex()) || other.toLowerCase().contains(query.toLowerCase().toRegex()) && !query.equals("All")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Reference

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}

class ReferenceModel(property: ObjectProperty<Reference>) : ItemViewModel<Reference>(itemProperty = property) {
    val id = bind { item?.idProperty() }
    val make = bind { item?.makeProperty() }
    val model = bind { item?.modelProperty() }
    val year = bind { item?.yearProperty() }
    val chassisCode = bind { item?.chassisCodeProperty() }
    val other = bind { item?.otherProperty() }
    val engineLiter = bind { item?.engineLiterProperty() }
    val aspiration = bind { item?.aspirationProperty() }
    val driveType = bind { item?.driveTypeProperty() }
    val numDoor = bind { item?.numDoorProperty() }
    val bodyType = bind { item?.bodyTypeProperty() }
    val fuelType = bind { item?.fuelTypeProperty() }
    val submodel = bind { item?.submodelProperty() }
    val engineCode = bind { item?.engineCodeProperty() }
    val comment = bind { item?.commentProperty() }
}


