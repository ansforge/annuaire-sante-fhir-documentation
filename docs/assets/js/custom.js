const contactEmail = 'LT-DTEX-Annuaire@esante.gouv.fr';

function normalizeText(s){
   return s.replace(/[^\w ]/g, '')
}
/**
 * Code tabs generation.
 * This code that use jquery will create tabs for code snip.
 */
jQuery(() => {
    // for each tabs
    jQuery('div.code-sample').each((i,elemRaw) => {
        var li = [];
        var elem = jQuery(elemRaw);

        // find all contents to build ids:
        elem.find('.tab-content').each((k,subElemRaw) => {
            var subElem = jQuery(subElemRaw);
            var currentId = 'sub-'+normalizeText(subElem.attr('data-name'));
            li.push({
                tab: currentId,
                label: subElem.attr('data-name')
            })
            subElem.attr('id', currentId);
        });

        // generate tabs:
        var stringTabs = '';
        for(var i=0;i<li.length;i++){
            var liElem  = li[i];
            stringTabs = stringTabs + '<li><a data-tab="'+liElem.tab+'">'+  liElem.label +'</a></li>'
        }
        elem.prepend('<ul class="tab">'+stringTabs+'</ul>');

        // tab logic:
        elem.find('li a').click((event) => {
            const link = jQuery(event.target);
            const target = link.attr('data-tab');
            link.parents('ul.tab').find('.active').removeClass('active');
            link.parent().addClass('active');
            link.parents('.code-sample').find('.tab-content').each((j, tabRaw) => {
                const tab = jQuery(tabRaw);
                if(tab.attr('id') === target){
                    tab.show();
                }else{
                    tab.hide();
                }
            })
        })

        // click the first tab:
        elem.find('li a')[0].click();

    });

    const email = jQuery('#contact-email');

    if (email && email.length > 0) {
       email[0].innerText = contactEmail;
       jQuery(email[0]).attr('href', `mailto:${contactEmail}`);
    }
})
