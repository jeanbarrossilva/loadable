package com.jeanbarrossilva.loadable.placeholder

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.ifLoaded

/**
 * Holds place for large, loading [Text].
 *
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 **/
@Composable
fun LargeTextualPlaceholder(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color
) {
    LargeTextualPlaceholder(isLoading = true, style, color, modifier)
}

/**
 * Holds place for large, loading [Text].
 *
 * @param loadable [Loadable] that determines whether the [content] is shown.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
fun LargeTextualPlaceholder(
    loadable: Loadable<String>,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color,
    content: @Composable (text: String) -> Unit
) {
    LargeTextualPlaceholder(isLoading = loadable is Loadable.Loading, style, color, modifier) {
        loadable.ifLoaded {
            content(this)
        }
    }
}

/**
 * Holds place for medium, loading [Text].
 *
 * @param loadable [Loadable] that determines whether the [content] is shown.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
fun MediumTextualPlaceholder(
    loadable: Loadable<String>,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color,
    content: @Composable (text: String) -> Unit
) {
    MediumTextualPlaceholder(isLoading = loadable is Loadable.Loading, style, color, modifier) {
        loadable.ifLoaded {
            content(this)
        }
    }
}

/**
 * Holds place for medium, loading [Text].
 *
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 **/
@Composable
fun MediumTextualPlaceholder(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color
) {
    MediumTextualPlaceholder(isLoading = true, style, color, modifier)
}

/**
 * Holds place for small, loading [Text].
 *
 * @param loadable [Loadable] that determines whether the [content] is shown.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
fun SmallTextualPlaceholder(
    loadable: Loadable<String>,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color,
    content: @Composable (text: String) -> Unit
) {
    SmallTextualPlaceholder(isLoading = loadable is Loadable.Loading, style, color, modifier) {
        loadable.ifLoaded {
            content(this)
        }
    }
}

/**
 * Holds place for small, loading [Text].
 *
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 **/
@Composable
fun SmallTextualPlaceholder(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = PlaceholderDefaults.color
) {
    SmallTextualPlaceholder(isLoading = true, style, color, modifier)
}

/**
 * Holds place for large, loading [Text].
 *
 * @param isLoading Whether the placeholder is visible (instead of the [content]).
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
private fun LargeTextualPlaceholder(
    isLoading: Boolean,
    style: TextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    TextualPlaceholder(isLoading, fraction = 1f, style, color, modifier, content)
}

/**
 * Holds place for medium, loading [Text].
 *
 * @param isLoading Whether the placeholder is visible (instead of the [content]).
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
private fun MediumTextualPlaceholder(
    isLoading: Boolean,
    style: TextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    TextualPlaceholder(isLoading, fraction = .5f, style, color, modifier, content)
}

/**
 * Holds place for small, loading [Text].
 *
 * @param isLoading Whether the placeholder is visible (instead of the [content]).
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param content [Text] that's shown if the [loadable] is [loaded][Loadable.Loaded].
 **/
@Composable
private fun SmallTextualPlaceholder(
    isLoading: Boolean,
    style: TextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    TextualPlaceholder(isLoading, fraction = .2f, style, color, modifier, content)
}

/**
 * Holds place for loading [Text].
 *
 * @param isLoading Whether the placeholder is visible (instead of the [content]).
 * @param fraction Available-width-based fraction.
 * @param style [TextStyle] for determining the height.
 * @param color [Color] by which the [TextualPlaceholder] is colored.
 * @param modifier [Modifier] to be applied to the underlying [Placeholder].
 * @param content [Text] that's shown if [isLoading] is `false`.
 **/
@Composable
private fun TextualPlaceholder(
    isLoading: Boolean,
    fraction: Float,
    style: TextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    val density = LocalDensity.current
    val height = remember(style, density) {
        with(density) {
            style.fontSize.toDp()
        }
    }

    Placeholder(
        modifier
            .`if`(isLoading) { requiredHeight(height) }
            .`if`(isLoading) { fillMaxWidth(fraction) },
        isLoading,
        shapeFor(style),
        color
    ) {
        ProvideTextStyle(style, content)
    }
}

/**
 * [Shape] that's equivalent to the [textStyle].
 *
 * @param textStyle [TextStyle] for which the [Shape] is.
 **/
@Composable
private fun shapeFor(textStyle: TextStyle): Shape {
    return when (textStyle) {
        MaterialTheme.typography.displayLarge,
        MaterialTheme.typography.displayMedium,
        MaterialTheme.typography.displaySmall,
        MaterialTheme.typography.headlineLarge,
        MaterialTheme.typography.headlineMedium,
        MaterialTheme.typography.headlineSmall -> MaterialTheme.shapes.large
        MaterialTheme.typography.labelLarge,
        MaterialTheme.typography.labelMedium,
        MaterialTheme.typography.labelSmall -> MaterialTheme.shapes.small
        else -> PlaceholderDefaults.shape
    }
}

/** Preview of a [LargeTextualPlaceholder]. **/
@Composable
@Preview
private fun LargeTextualPlaceholderPreview() {
    MaterialTheme {
        LargeTextualPlaceholder()
    }
}

/** Preview of a [MediumTextualPlaceholder]. **/
@Composable
@Preview
private fun MediumTextualPlaceholderPreview() {
    MaterialTheme {
        MediumTextualPlaceholder()
    }
}

/** Preview of a [SmallTextualPlaceholder]. **/
@Composable
@Preview
private fun SmallTextualPlaceholderPreview() {
    MaterialTheme {
        SmallTextualPlaceholder()
    }
}
