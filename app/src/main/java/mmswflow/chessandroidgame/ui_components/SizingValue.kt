package mmswflow.chessandroidgame.ui_components

sealed class SizingValue(val value: Int) {

    object SmallActionButtonWidth : SizingValue(200)
    object SmallActionButtonBoxPadding: SizingValue(6)
    object SmallActionButtonBorderWidth: SizingValue(2)
    object SmallActionButtonTextFontSize: SizingValue(16)
    object SmallActionButtonPadding: SizingValue(2)

    object SmallActionButtonRoundedCornerShapeSize: SizingValue(16)

    object HeaderFontSize: SizingValue(42)
    object HeaderPaddingSize: SizingValue(64)

    object SmallInfoTextFontSize: SizingValue(16)
    object SmallInfoTextPadding: SizingValue(8)

    object MediumInfoTextFontSize: SizingValue(24)
    object MediumInfoTextPadding : SizingValue(32)

    object LargeInfoTextFontSize : SizingValue(32)
    object LargeInfoTextPadding : SizingValue(42)

    object SelectionCardPadding : SizingValue(16)
    object SelectionCardRoundedCornerShapeSize: SizingValue(16)
    object SelectionCardBorderWidth : SizingValue(2)

    object SelectionIconIdentifierPadding : SizingValue(24)
}