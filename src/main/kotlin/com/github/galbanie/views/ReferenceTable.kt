package com.github.galbanie.views

import com.github.galbanie.controllers.ReferenceController
import com.github.galbanie.models.Reference
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-16.
 */
class ReferenceTable : View() {

    val refctrl : ReferenceController by inject()

    override val root = tableview(refctrl.references) {
        useMaxWidth = true

        column("#", Reference::id){
            pctWidth(5)
        }
        column("Make",Reference::make){
            pctWidth(10)
        }
        column("Model",Reference::model){
            pctWidth(10)
        }
        column("Year",Reference::year){
            pctWidth(5)
        }
        column("Liter",Reference::engineLiter){
            pctWidth(5)
        }
        column("Engine Code",Reference::engineCode){
            pctWidth(10)
        }
        column("Chassis Code",Reference::chassisCode){
            pctWidth(10)
        }
        column("Num Door",Reference::numDoor){
            pctWidth(5)
        }
        column("Body Type",Reference::bodyType){
            pctWidth(10)
        }
        column("Drive Type",Reference::driveType){
            pctWidth(10)
        }
        column("Sub Model",Reference::submodel){
            pctWidth(10)
        }
        column("Fuel Type",Reference::fuelType){
            pctWidth(10)
        }

        columnResizePolicy = SmartResize.POLICY

    }

}
