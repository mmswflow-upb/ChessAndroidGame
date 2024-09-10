package mmswflow.chessandroidgame.ui_components

sealed class SizingValue(val value: Int) {

    object SmallActionButtonWidth : SizingValue(200)
    object SmallActionButtonBoxPadding: SizingValue(6)
    object SmallActionButtonBorderWidth: SizingValue(2)
    object SmallActionButtonTextFontSize: SizingValue(16)
    object SmallActionButtonPadding: SizingValue(2)

    object HeaderFontSize: SizingValue(32)
    object HeaderPaddingSize: SizingValue(64)

    object SmallInfoTextFontSize: SizingValue(16)
    object SmallInfoTextPadding: SizingValue(8)
}