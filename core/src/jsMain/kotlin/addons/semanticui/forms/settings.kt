package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.CheckboxOptions
import de.peekandpoke.kraft.addons.forms.FieldOptions
import de.peekandpoke.kraft.addons.forms.InputOptions
import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.ultra.common.TypedKey
import de.peekandpoke.ultra.semanticui.*
import kotlinx.html.DIV
import kotlinx.html.InputType

interface SemanticOptions<T> : FieldOptions<T> {

    companion object {
        val appearKey = TypedKey<SemanticFn>("appear")
    }

    interface Checkbox<T> : CheckboxOptions<T> {

        companion object {
            val styleKey = TypedKey<SemanticFn>("style")
        }

        @KraftFormsSettingDsl
        val style get() = access(styleKey)

        @KraftFormsSettingDsl
        fun toggle() {
            style { toggle }
        }

        @KraftFormsSettingDsl
        fun slider() {
            style { slider }
        }
    }

    interface Input<T> : InputOptions<T> {
        companion object {
            val wrapFieldWithKey = TypedKey<SemanticFn>("wrap-field-with")
            val beforeFieldKey = TypedKey<DIV.(UiInputFieldComponent<*, *>) -> Unit>("before-field")
            val afterFieldKey = TypedKey<DIV.(UiInputFieldComponent<*, *>) -> Unit>("after-field")
        }

        @JsName("rightIcon")
        @KraftFormsSettingDsl
        fun rightIcon(iconFn: SemanticIconFn) {
            attributes[wrapFieldWithKey] = semantic { right.icon.input }

            val fn: DIV.(UiInputFieldComponent<*, *>) -> Unit = { icon.iconFn().invoke() }
            attributes[afterFieldKey] = fn
        }

        @JsName("clearingRightIcon")
        @KraftFormsSettingDsl
        fun rightClearingIcon(iconFn: SemanticIconFn = { times }) {
            attributes[wrapFieldWithKey] = semantic { right.icon.input }

            val fn: DIV.(UiInputFieldComponent<*, *>) -> Unit = {
                icon.link.iconFn().invoke {
                    onClick { _ ->
                        it.setInput("")
                        it.focus("input")
                    }
                }
            }

            attributes[afterFieldKey] = fn
        }

        @JsName("revealRevealPasswordIcon")
        @KraftFormsSettingDsl
        fun revealRevealPasswordIcon() {
            attributes[wrapFieldWithKey] = semantic { right.icon.input }

            val fn: DIV.(UiInputFieldComponent<*, *>) -> Unit = {

                val type = it.effectiveType ?: InputType.password

                val iconFn = when (type) {
                    InputType.password -> semanticIcon { eye_outline }
                    else -> semanticIcon { eye_slash_outline }
                }

                icon.link.iconFn().invoke {
                    onClick { _ ->
                        it.typeOverride = when (type) {
                            InputType.password -> InputType.text
                            else -> InputType.password
                        }
                    }
                }
            }

            attributes[afterFieldKey] = fn
        }

        @JsName("leftIcon")
        @KraftFormsSettingDsl
        fun leftIcon(iconFn: SemanticIconFn) {
            attributes[wrapFieldWithKey] = semantic { left.icon.input }

            val fn: DIV.(UiInputFieldComponent<*, *>) -> Unit = { icon.iconFn().invoke() }
            attributes[afterFieldKey] = fn
        }

        @JsName("rightLabel")
        @KraftFormsSettingDsl
        fun rightLabel(labelFn: DIV.(UiInputFieldComponent<T, *>) -> Unit) {
            attributes[wrapFieldWithKey] = semantic { right.labeled.input }

            @Suppress("UNCHECKED_CAST")
            attributes[afterFieldKey] = labelFn as DIV.(UiInputFieldComponent<*, *>) -> Unit
        }

        @JsName("leftLabel")
        @KraftFormsSettingDsl
        fun leftLabel(labelFn: DIV.(UiInputFieldComponent<T, *>) -> Unit) {
            attributes[wrapFieldWithKey] = semantic { left.labeled.input }

            @Suppress("UNCHECKED_CAST")
            attributes[beforeFieldKey] = labelFn as DIV.(UiInputFieldComponent<*, *>) -> Unit
        }

        @KraftFormsSettingDsl
        fun wrapFieldWith(): (SemanticTag.() -> SemanticTag)? = attributes[wrapFieldWithKey]

        @KraftFormsSettingDsl
        fun renderBeforeField(): (DIV.(UiInputFieldComponent<*, *>) -> Unit)? = attributes[beforeFieldKey]

        @KraftFormsSettingDsl
        fun renderAfterField(): (DIV.(UiInputFieldComponent<*, *>) -> Unit)? = attributes[afterFieldKey]
    }

    @KraftFormsSettingDsl
    val appear get() = access(appearKey)
}

