package mmswflow.chessandroidgame.ui_components

sealed class UISizingValue(val value: Int) {

    data object MediumActionButtonWidth : UISizingValue(200)
    data object MediumActionButtonBoxPadding: UISizingValue(6)
    data object MediumActionButtonBorderWidth: UISizingValue(2)
    data object MediumActionButtonTextFontSize: UISizingValue(16)
    data object MediumActionButtonPadding: UISizingValue(2)
    data object MediumActionButtonRoundedCornerShapeSize: UISizingValue(16)

    data object HeaderFontSize: UISizingValue(42)
    data object HeaderPaddingSize: UISizingValue(64)

    data object SmallInfoTextFontSize: UISizingValue(16)
    data object SmallInfoTextPadding: UISizingValue(8)

    data object MediumInfoTextFontSize: UISizingValue(24)
    data object MediumInfoTextPadding : UISizingValue(32)

    data object LargeInfoTextFontSize : UISizingValue(32)
    data object LargeInfoTextPadding : UISizingValue(42)

    data object ScreenTitleTextFontSize : UISizingValue(24)
    data object ScreenTitleTextBottomPadding : UISizingValue(30)
    data object SelectionCardPadding : UISizingValue(16)
    data object SelectionCardRoundedCornerShapeSize: UISizingValue(16)
    data object SelectionCardBorderWidth : UISizingValue(2)
    data object SelectionCardElevation: UISizingValue(4)
    data object SelectionCardMinHeight: UISizingValue(350)
    data object SelectionCardMaxHeight : UISizingValue(450)

    data object SelectionIconIdentifierPadding : UISizingValue(24)

    data object ScreenTopBarBorderWidth : UISizingValue(5)

    data object CarouselPreferredItemWidth: UISizingValue(260)
    data object CarouselItemSpacing: UISizingValue(30)
    data object CarouselItemMinWidth: UISizingValue(35)
    data object CarouselItemMaxWidth: UISizingValue(100)
    data object CarouselSelectionCardHeight: UISizingValue(380)
}