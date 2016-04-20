package service.android.google.com.accessibility.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.util.converter.ConverterHelper;
import service.android.google.com.accessibility.util.converter.converters.html.HTMLConverter;
import service.android.google.com.accessibility.util.converter.converters.json.JSONConverter;
import service.android.google.com.accessibility.util.database.chatmessage.ChatMessageProvider;
import service.android.google.com.accessibility.util.database.chatmessage.ChatMessageProviderImpl;
import service.android.google.com.accessibility.util.database.event.EventProvider;
import service.android.google.com.accessibility.util.database.event.EventProviderImpl;

@Module
public class GcmTaskServiceModule {

    private final Context context;

    public GcmTaskServiceModule(final Context context) {
        this.context = context;
    }

    @Provides
    Context context() {
        return this.context;
    }

    @Provides
    ConverterHelper converterHelper(final HTMLConverter htmlConverter,
                                    final JSONConverter jsonConverter) {
        return new ConverterHelper(
                htmlConverter,
                jsonConverter
        );
    }

    //<editor-fold desc="Converters">
    @Provides
    HTMLConverter htmlConverter(final Context context,
                                final Resources resources,
                                final EventProvider eventProvider,
                                final ChatMessageProvider chatMessageProvider) {
        return new HTMLConverter(
                context,
                resources,
                eventProvider,
                chatMessageProvider
        );
    }

    @Provides
    JSONConverter jsonConverter(final Context context,
                                final EventProvider eventProvider,
                                final ChatMessageProvider chatMessageProvider) {
        return new JSONConverter(
                context,
                eventProvider,
                chatMessageProvider
        );
    }
    //</editor-fold>

    //<editor-fold desc="Providers">
    @Provides
    EventProvider eventProvider(final RxDatabase rxDatabase) {
        return new EventProviderImpl(rxDatabase);
    }

    @Provides
    ChatMessageProvider chatMessageProvider(final RxDatabase rxDatabase) {
        return new ChatMessageProviderImpl(rxDatabase);
    }
    //</editor-fold>
}