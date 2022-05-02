package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.GenericFormField
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.SemanticFn
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.Tag
import kotlinx.html.input

@KraftFormsDsl
val Tag.UiInputField get() = UiInputFieldDef.InputRenderer(this)

@KraftFormsDsl
val Tag.UiDateField get() = UiInputFieldDef.DateRenderer(this)

@KraftFormsDsl
val Tag.UiDateTimeField get() = UiInputFieldDef.DateTimeRenderer(this)

@KraftFormsDsl
val Tag.UiTimeField get() = UiInputFieldDef.TimeRenderer(this)

/**
 * Semantic ui input field definition
 */
object UiInputFieldDef : GenericFormField.Definition {

    class InputRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForNumbers

    class DateRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForDates

    class DateTimeRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForDateTimes

    class TimeRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForTimes

    override fun <T> GenericFormField<T>.content(vdom: VDom) {
        with(vdom) {

            val appear: SemanticFn = settings.semantic.appear

            ui.appear().given(hasErrors) { error }.field {

                renderLabel("input")

                input {
                    applyAll()
                }

                renderErrors(this)
            }
        }
    }
}
