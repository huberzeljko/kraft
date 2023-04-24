@file:Suppress("Detekt.TooManyFunctions", "unused", "UnsafeCastFromDynamic")

package de.peekandpoke.kraft.components

import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.js.*
import org.w3c.dom.events.*

/**
 * Adds an onAnimationEnd handler.
 *
 * The event is raised when a css animation ends.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/animationend_event
 */
fun CommonAttributeGroupFacade.onAnimationEnd(handler: (Event) -> Unit) {
    consumer.onTagEvent(this, "onanimationend", handler.asDynamic())
}

/**
 * Adds an onBlur handler.
 *
 * This event does NOT bubble while [onFocusOut] does.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/blur_event
 */
fun CommonAttributeGroupFacade.onBlur(handler: (Event) -> Unit) {
    onBlurFunction = handler.asDynamic()
}

/**
 * Adds an onChange handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/change_event
 */
fun CommonAttributeGroupFacade.onChange(handler: (Event) -> Unit) {
    onChangeFunction = handler.asDynamic()
}

/**
 * Adds an onClick handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/click_event
 */
fun CommonAttributeGroupFacade.onClick(handler: (MouseEvent) -> Unit) {
    onClickFunction = handler.asDynamic()
}

/**
 * Adds an onContextMenu handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/contextmenu_event
 */
fun CommonAttributeGroupFacade.onContextMenu(handler: (UIEvent) -> Unit) {
    onContextMenuFunction = handler.asDynamic()
}

/**
 * Adds an onContextMenu handler and prevents the default.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/contextmenu_event
 */
fun CommonAttributeGroupFacade.onContextMenuPreventingDefault(handler: (UIEvent) -> Unit) {
    onContextMenuFunction = { e ->
        e.preventDefault()
        handler(e.asDynamic())
    }
}

/**
 * Adds an onClick handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/error_event
 */
fun CommonAttributeGroupFacade.onError(handler: (Event) -> Unit) {
    onErrorFunction = handler.asDynamic()
}

/**
 * Adds an onFocus handler.
 *
 * This event does NOT bubble while [onFocusIn] does.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focus_event
 */
fun CommonAttributeGroupFacade.onFocus(handler: (FocusEvent) -> Unit) {
    onFocusFunction = handler.asDynamic()
}

/**
 * Add an onFocusIn handler.
 *
 * The event DOES bubble while [onFocus] does not.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focusin_event
 */
fun CommonAttributeGroupFacade.onFocusIn(handler: (FocusEvent) -> Unit) {
    onFocusInFunction = handler.asDynamic()
}

/**
 * Add an onFocusOut handler.
 *
 * This event DOES bubble while [onBlur] does not.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focusout_event
 */
fun CommonAttributeGroupFacade.onFocusOut(handler: (FocusEvent) -> Unit) {
    onFocusOutFunction = handler.asDynamic()
}

/**
 * Adds an onInput handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/input_event
 */
fun CommonAttributeGroupFacade.onInput(handler: (InputEvent) -> Unit) {
    onInputFunction = handler.asDynamic()
}

/**
 * Adds an onKeyDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keydown_event
 */
fun CommonAttributeGroupFacade.onKeyDown(handler: (KeyboardEvent) -> Unit) {
    onKeyDownFunction = handler.asDynamic()
}

/**
 * Adds an onKeyPress handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keypress_event
 */
@Deprecated("Use onKeyDown instead", ReplaceWith("onKeyDown"))
fun CommonAttributeGroupFacade.onKeyPress(handler: (KeyboardEvent) -> Unit) {
    onKeyPressFunction = handler.asDynamic()
}

/**
 * Adds an onKeyUp handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keyup_event
 */
fun CommonAttributeGroupFacade.onKeyUp(handler: (KeyboardEvent) -> Unit) {
    onKeyUpFunction = handler.asDynamic()
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mousedown_event
 */
fun CommonAttributeGroupFacade.onMouseDown(handler: (MouseEvent) -> Unit) {
    onMouseDownFunction = handler.asDynamic()
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseenter_event
 */
fun CommonAttributeGroupFacade.onMouseEnter(handler: (MouseEvent) -> Unit) {
    consumer.onTagEvent(this, "onmouseenter", handler.asDynamic())
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseleave_event
 */
fun CommonAttributeGroupFacade.onMouseLeave(handler: (MouseEvent) -> Unit) {
    consumer.onTagEvent(this, "onmouseleave", handler.asDynamic())
}

/**
 * Adds an onMouseMove handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mousemove_event
 */
fun CommonAttributeGroupFacade.onMouseMove(handler: (MouseEvent) -> Unit) {
    onMouseMoveFunction = handler.asDynamic()
}

/**
 * Adds an onMouseOver handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseover_event
 */
fun CommonAttributeGroupFacade.onMouseOver(handler: (MouseEvent) -> Unit) {
    onMouseOverFunction = handler.asDynamic()
}

/**
 * Adds an onMouseOut handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseout_event
 */
fun CommonAttributeGroupFacade.onMouseOut(handler: (MouseEvent) -> Unit) {
    onMouseOutFunction = handler.asDynamic()
}

/**
 * Adds an onMouseUp handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseup_event
 */
fun CommonAttributeGroupFacade.onMouseUp(handler: (MouseEvent) -> Unit) {
    onMouseUpFunction = handler.asDynamic()
}

/**
 * Add an onSelect handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/select_event
 */
fun CommonAttributeGroupFacade.onSelect(handler: (InputEvent) -> Unit) {
    onSelectFunction = handler.asDynamic()
}

/**
 * onSubmit handler
 */
fun CommonAttributeGroupFacade.onSubmit(handler: (Event) -> Unit) {
    onSubmitFunction = {
        it.preventDefault()
        handler(it.asDynamic())
    }
}
