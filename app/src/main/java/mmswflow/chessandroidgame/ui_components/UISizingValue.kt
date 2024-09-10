package mmswflow.chessandroidgame.ui_components

sealed class UISizingValue(val value: Int) {

    object MediumActionButtonWidth : UISizingValue(200)
    object MediumActionButtonBoxPadding: UISizingValue(6)
    object MediumActionButtonBorderWidth: UISizingValue(2)
    object MediumActionButtonTextFontSize: UISizingValue(16)
    object MediumActionButtonPadding: UISizingValue(2)

    object MediumActionButtonRoundedCornerShapeSize: UISizingValue(16)

    object HeaderFontSize: UISizingValue(42)
    object HeaderPaddingSize: UISizingValue(64)

    object SmallInfoTextFontSize: UISizingValue(16)
    object SmallInfoTextPadding: UISizingValue(8)

    object MediumInfoTextFontSize: UISizingValue(24)
    object MediumInfoTextPadding : UISizingValue(32)

    object LargeInfoTextFontSize : UISizingValue(32)
    object LargeInfoTextPadding : UISizingValue(42)

    object ScreenTitleTextFontSize : UISizingValue(20)
    object SelectionCardPadding : UISizingValue(16)
    object SelectionCardRoundedCornerShapeSize: UISizingValue(16)
    object SelectionCardBorderWidth : UISizingValue(2)
    object SelectionCardElevation: UISizingValue(4)

    object SelectionCardMinHeight: UISizingValue(300)
    object SelectionCardMaxHeight : UISizingValue(450)

    object SelectionIconIdentifierPadding : UISizingValue(24)
}