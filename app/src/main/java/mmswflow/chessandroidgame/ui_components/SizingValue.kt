package mmswflow.chessandroidgame.ui_components

sealed class SizingValue(val value: Int) {

    object SmallActionButtonBoxPadding: SizingValue(2)
    object SmallActionButtonBorderWidth: SizingValue(2)
    object SmallActionButtonTextSize: SizingValue(16)
    object SmallActionButtonPadding: SizingValue(2)

    object HeaderFontSize: SizingValue(32)
    object HeaderPaddingSize: SizingValue(16)

    object SmallInfoTextFontSize: SizingValue(16)
    object SmallInfoTextPadding: SizingValue(8)
}